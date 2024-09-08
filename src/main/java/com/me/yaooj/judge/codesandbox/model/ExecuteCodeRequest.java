package com.me.yaooj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {


    private List<String> inputList;


    private String code;


    private String language;

    // todo 这里有个小问题就是代码运行时间限制，思考一下是否要加，提供方向根据实际业务来定
}
