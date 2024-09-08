package com.me.yaooj.judge.strategy;

import com.me.yaooj.judge.codesandbox.model.JudgeInfo;
import com.me.yaooj.judge.strategy.model.JudgeContext;
;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    JudgeInfo executeJudge(JudgeContext judgeContext);
}
