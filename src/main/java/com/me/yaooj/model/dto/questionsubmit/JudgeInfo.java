package com.me.yaooj.model.dto.questionsubmit;

import lombok.Builder;
import lombok.Data;

/**
 * 判题信息
 */
@Data
@Builder
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存（KB）
     */
    private Long memory;

    /**
     * 消耗时间（ms）
     */
    private Long time;
}
