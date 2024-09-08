package com.me.yaooj.judge.codesandbox.impl;

import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现有的代码沙箱）
 */
public class ThirdPartySandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
