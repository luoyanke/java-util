package com.sandboat.javautil.pdfbox;

import cn.huacloud.platform.sdk.client.AIOpenClient;
import cn.huacloud.platform.sdk.result.PdfOcrResult;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author: lyk
 * @time: 2019-12-18 18:22
 * @description:
 */
public class WriteToPDFUtil {


    public static void writeString(String filePath) throws IOException {
        // 创建客户端
        AIOpenClient aiOpenClient = new AIOpenClient("http://ai.hua-cloud.com.cn:8072/api", "test", "123456");

        File file = new File(filePath);
       // List<PdfOcrResult> pdfOcrResults = aiOpenClient.pdfOcrHandle(new FileInputStream(file));


        PDDocument document = PDDocument.load(file);



        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        PDType1Font font = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font, 12);

            try {
                contentStream.showText("11111");
                System.out.println(1);
                contentStream.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        contentStream.endText();


        contentStream.close();



        //每一页
       /* for (PdfOcrResult result : pdfOcrResults) {
            PDPage page = document.getPage(result.getIndex());
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType1Font font = PDType1Font.TIMES_ROMAN;
            contentStream.beginText();
            contentStream.setFont(font, 12);
            result.getTexts().stream().forEach(text -> {

                try {
                    contentStream.showText(text.getContent());
                    System.out.println(text.getContent());
                    contentStream.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            contentStream.endText();


            contentStream.close();
        }*/

        document.close();


    }

    public static void main(String[] args) {
        try {
            WriteToPDFUtil.writeString("/Users/luoyanke/Desktop/ffffffffff/7afb6e94aa7940a3ad9e45081a052740.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
