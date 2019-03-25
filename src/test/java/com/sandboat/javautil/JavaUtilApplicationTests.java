package com.sandboat.javautil;

import com.sandboat.javautil.pdfbox.ReadPdfUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaUtilApplicationTests {

    @Test
    public void contextLoads() {



    }

    @Test
    public void testExtractText() {
        try {
            String s = ReadPdfUtil.extractText("E:\\办公文档\\阅卷系统\\故意伤害罪\\李治春故意伤害案\\判决书.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
