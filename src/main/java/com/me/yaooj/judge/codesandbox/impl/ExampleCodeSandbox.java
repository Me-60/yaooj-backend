package com.me.yaooj.judge.codesandbox.impl;

import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;
import com.me.yaooj.judge.codesandbox.model.JudgeInfo;
import com.me.yaooj.model.enums.JudgeInfoMessageEnum;
import com.me.yaooj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱（测试业务流程沙箱）
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例代码沙箱");
        List<String> inputList = executeCodeRequest.getInputList();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("===执行测试成功===");
        executeCodeResponse.setJudgeInfo(judgeInfo);
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        return executeCodeResponse;
    }
}
