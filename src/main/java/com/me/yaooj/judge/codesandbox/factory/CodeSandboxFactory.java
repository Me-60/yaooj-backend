package com.me.yaooj.judge.codesandbox.factory;

import com.me.yaooj.judge.codesandbox.CodeSandbox;
import com.me.yaooj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.me.yaooj.judge.codesandbox.impl.RemoteSandbox;
import com.me.yaooj.judge.codesandbox.impl.ThirdPartySandbox;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeRequest;
import com.me.yaooj.judge.codesandbox.model.ExecuteCodeResponse;
import com.me.yaooj.model.enums.QuestionSubmitLanguageEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 代码沙箱工厂（根据 type 参数匹配返回创建对应的代码沙箱实例）
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteSandbox();
            case "thirdParty":
                return new ThirdPartySandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

    /**
     * 代码沙箱工厂测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        while (myScanner.hasNext()) {
            String type = myScanner.next();
            CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
            String code = "int main() {}";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
            executeCodeRequest.setCode(code);
            executeCodeRequest.setLanguage(language);
            executeCodeRequest.setInputList(inputList);

            ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        }
    }
}
