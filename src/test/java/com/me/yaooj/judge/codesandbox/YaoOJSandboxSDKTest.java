package com.me.yaooj.judge.codesandbox;

import com.me.yaoojcodesandboxsdk.client.YaoOJCodeSandboxClient;
import com.me.yaooj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class YaoOJSandboxSDKTest {

    @Resource
    private YaoOJCodeSandboxClient yaoOJCodeSandboxClient;

    @Test
    public void test() {

        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果为\" + (a + b));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");

        com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest executeCodeRequest1 = new com.me.yaoojcodesandboxsdk.model.ExecuteCodeRequest();
        executeCodeRequest1.setCode(code);
        executeCodeRequest1.setLanguage(language);
        executeCodeRequest1.setInputList(inputList);

        String codeSandboxExecResponse = yaoOJCodeSandboxClient.getCodeSandboxExecResponse(executeCodeRequest1);

        System.out.println("====响应数据====" + codeSandboxExecResponse);
    }
}
