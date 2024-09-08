package com.me.yaooj.judge.strategy.impl;

import cn.hutool.json.JSONUtil;
import com.me.yaooj.judge.codesandbox.model.JudgeInfo;
import com.me.yaooj.judge.strategy.JudgeStrategy;
import com.me.yaooj.judge.strategy.model.JudgeContext;
import com.me.yaooj.model.dto.question.JudgeCase;
import com.me.yaooj.model.dto.question.JudgeConfig;
import com.me.yaooj.model.entity.Question;
import com.me.yaooj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategyImpl implements JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo executeJudge(JudgeContext judgeContext) {

        // 获取提交习题代码实际运行所需的各类限制
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long actualMemory = judgeInfo.getMemory();
        Long actualTime = judgeInfo.getTime();

        // 设置提交习题的部分判题信息（这里有点小问题，使用new的方式来创建对象的话会报错，错误为JudgeInfo为private类型无法访问）
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(actualTime);
        judgeInfoResponse.setMemory(actualMemory);

        // 判题结果信息先默认为通过，之后根据判题逻辑流程来确定最终的信息是什么并设置判题信息
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;

        // 获取习题规定的输入用例
        List<String> inputList = judgeContext.getInputList();
        // 获取习题提交代码实际运行的输出用例
        List<String> outputList = judgeContext.getOutputList();
        // 获取习题规定的判题用例
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        // 先判断沙箱执行结果输出数量是否和预期输出数量相等，因为判题用例是输入与输出一组的形式，所以输入和输出用例数是一致的
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        // 依次判断每一项输出和预期输出是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }

        // 判断习题限制
        // 获取习题规定代码运行各类限制
        Question question = judgeContext.getQuestion();
        String judgeConfigStr = question.getJudgeConfig();
        // 设计字符串转化为对象，目前猜测前端请求中为JSON字符串，需要处理
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long timeLimit = judgeConfig.getTimeLimit();
        Long memoryLimit = judgeConfig.getMemoryLimit();

        // 比较实际与规定限制大小，来判断提交习题代码是否通过
        if (actualMemory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (actualTime > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        // 经历上述判题逻辑流程后，符合习题规定限制后，需将最初设置通过默认值写入判题信息
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
