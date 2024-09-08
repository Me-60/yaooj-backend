package com.me.yaooj.judge.codesandbox.proxy;

import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理类
 * 为了了解代码沙箱请求和响应日志，实现方式有
 * 1.每个代码沙箱实现类中编写日志输出
 * 2.通过使用代理模式的思维来编写一个代理类并实现代码沙箱接口
 * 第一种方法比较繁琐
 * 第二种方法相对容易，使用的是静态代理，思路如下
 * 实现被代理的接口，通过构造函数接受一个被代理的接口实现类（下方通过lombok注解编写构造函数），
 * 调用被代理的接口实现类，在调用前后增加对应的操作，如打印请求和响应日志
 */
@Slf4j
@AllArgsConstructor
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
