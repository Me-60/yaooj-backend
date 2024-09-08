package com.me.yaooj.judge.strategy.model;

import com.me.yaooj.judge.codesandbox.model.JudgeInfo;
import com.me.yaooj.model.dto.question.JudgeCase;
import com.me.yaooj.model.entity.Question;
import com.me.yaooj.model.entity.QuestionSubmit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 执行判题策略所需要的参数（习题规定条件与习题代码实际运行结果）
 */
@Data
@Builder
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
