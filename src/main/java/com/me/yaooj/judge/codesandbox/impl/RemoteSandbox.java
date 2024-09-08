package com.me.yaooj.judge.codesandbox.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.me.yaooj.common.ErrorCode;
import com.me.yaooj.exception.BusinessException;
import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteSandbox implements CodeSandbox {

    private final static String URL = "http://localhost:8848/execCode";

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9rCZOTrr0W5iWRz6mQFUMKfUSyvpmNRj5ZNzNmmtN93nrbcjeWSWghOSdDIGc/piJOSSLcg+YuG4M97j+P7DiIPUSf56H/BcHoovQvAfxWYJ+diGfqPksF+Q4BH81yEebLjwuh3Rub9A+SOnJytqZSc6Mcli7U82PLaF2oHAFKwIDAQAB";

    private final static String USER_UUID = "d0a5df71-4ac5-4d33-9c9d-d9f8454cc587";

    private final static String USERNAME = "yaooj";

    // @Resource
    // private YaoOJCodeSandboxClient yaoOJCodeSandboxClient;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");

        System.out.println(executeCodeRequest);
        // 这里为了实现代码沙箱的sdk，并且为了数据统一，调用sdk中的请求
        // com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest executeCodeRequest1 = new com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest();
        // executeCodeRequest1.setCode(executeCodeRequest.getCode());
        // executeCodeRequest1.setLanguage(executeCodeRequest.getLanguage());
        // executeCodeRequest1.setInputList(executeCodeRequest.getInputList());

        // System.out.println("====SDK====" + yaoOJCodeSandboxClient);

        // 下方是一个生成签名、加密签名和调用远程服务的处理过程
        final String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);

        final String sign = DigestUtil.md5Hex(USER_UUID, StandardCharsets.UTF_8);

        // 签名转化为byte数组，统一字符编码，利于加密解密
        final byte[] signBytes = sign.getBytes(StandardCharsets.UTF_8);

        final RSA rsa = new RSA(null, PUBLIC_KEY);

        final byte[] encrypted = rsa.encrypt(signBytes, KeyType.PublicKey);

        final String encoded = Base64.encode(encrypted);

        HashMap<String,String> headers = new HashMap<>();

        headers.put("encrypted",encoded);
        headers.put("nonce",RandomUtil.randomNumbers(6));
        headers.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        headers.put("username",USERNAME);

        final String responseStr = HttpUtil.createPost(URL)
                .addHeaders(headers)
                .body(jsonStr)
                .execute()
                .body();

        // String responseStr = yaoOJCodeSandboxClient.getCodeSandboxExecResponse(executeCodeRequest1);

        System.out.println("====响应数据====" + responseStr);

        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"executeCode remoteSandbox error,message = " + responseStr);
        }

        return JSONUtil.toBean(responseStr,ExecuteCodeResponse.class);
    }
}
