package com.me.yaooj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息消息枚举
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public enum JudgeInfoMessageEnum {

    ACCEPTED("ACCEPTED", "成功"),
    WRONG_ANSWER("WRONG_ANSWER", "答案错误"),
    COMPILE_ERROR("COMPILE_ERROR", "编译错误"),
    MEMORY_LIMIT_EXCEEDED("MEMORY_LIMIT_EXCEEDED", "内存溢出"),
    TIME_LIMIT_EXCEEDED("TIME_LIMIT_EXCEEDED", "超时"),
    PRESENTATION_ERROR("PRESENTATION_ERROR", "展示错误"),
    OUTPUT_LIMIT_EXCEEDED("OUTPUT_LIMIT_EXCEEDED", "输出溢出"),
    WAITING("WAITING", "等待中"),
    DANGEROUS_OPERATION("DANGEROUS_OPERATION", "危险操作"),
    RUNTIME_ERROR("RUNTIME_ERROR", "运行错误"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误");

    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
