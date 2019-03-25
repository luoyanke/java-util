package com.sandboat.javautil.pdfbox;

import ch.qos.logback.core.joran.conditional.ElseAction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadPdfUtil {

    /**
     * 提取pdf文本
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String extractText(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            StringBuilder sb = new StringBuilder();
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            for (int p = 1; p <= document.getNumberOfPages(); ++p) {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(p);
                stripper.setEndPage(p);
                sb.append(stripper.getText(document));
            }
            return sb.toString();
        }
    }

    /**
     * 获取区域文本
     *
     * @param filePath
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public static String extractTextByArea(String filePath, int x, int y, int width, int height) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            Rectangle rect = new Rectangle(x, y, width, height);
            stripper.addRegion("class1", rect);
            PDPage firstPage = document.getPage(0);
            stripper.extractRegions(firstPage);

            System.out.println("Text in the area:" + rect);
            return stripper.getTextForRegion("class1");
        }
    }


    public static String getAreaByText(String filePath, int x, int y, int width, int height) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            Rectangle rect = new Rectangle(x, y, width, height);
            stripper.addRegion("class1", rect);
            PDPage firstPage = document.getPage(0);
            stripper.extractRegions(firstPage);

            System.out.println("Text in the area:" + rect);
            return stripper.getTextForRegion("class1");
        }
    }



    public static void main(String[] args) throws IOException {
        String s = ReadPdfUtil.extractText("E:\\ai-reader\\ai-reader-doc\\卷宗样例\\合同诈骗罪\\观检起诉受[2017]52011400317号\\起诉意见书\\第 11 页.pdf");
        //System.out.println(s);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char char1 : s.toCharArray()) {
            if ('\r' ==char1)
                continue;
            else if ('\n'==char1)
                sb.append("<br/>");
            else
                sb.append("<span index=\""+i+"\">" + String.valueOf(char1) + "</span>");

            //
            i++;
        }
        System.out.println(sb.toString());
        s = ReadPdfUtil.extractTextByArea(
                "E:\\ai-reader\\ai-reader-doc\\卷宗样例\\合同诈骗罪\\观检起诉受[2017]52011400317号\\起诉意见书\\第 11 页.pdf",
                10, 480, 205, 60);
        System.out.println(s);

    }





}
