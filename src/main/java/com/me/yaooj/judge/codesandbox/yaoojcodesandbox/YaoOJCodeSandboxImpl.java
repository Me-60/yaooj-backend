package com.me.yaooj.judge.codesandbox.yaoojcodesandbox;

import cn.hutool.json.JSONUtil;
import com.me.yaoojcodesandboxsdk.client.YaoOJCodeSandboxClient;
import com.me.yaooj.common.ErrorCode;
import com.me.yaooj.exception.BusinessException;
import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class YaoOJCodeSandboxImpl implements CodeSandbox {

    @Resource
    private YaoOJCodeSandboxClient yaoOJCodeSandboxClient;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        System.out.println("====YaoOJ代码沙箱====");

        log.info("代码沙箱请求信息" + executeCodeRequest.toString());

        // 这里为了实现代码沙箱的sdk，并且为了数据统一，调用sdk中的请求
        com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest executeCodeRequest1 = new com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest();
        executeCodeRequest1.setCode(executeCodeRequest.getCode());
        executeCodeRequest1.setLanguage(executeCodeRequest.getLanguage());
        executeCodeRequest1.setInputList(executeCodeRequest.getInputList());

        String responseStr = yaoOJCodeSandboxClient.getCodeSandboxExecResponse(executeCodeRequest1);

        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"executeCode remoteSandbox error,message = " + responseStr);
        }

        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);

        log.info("代码沙箱响应信息" + executeCodeResponse.toString());

        return executeCodeResponse;
    }
}
