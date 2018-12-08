package com.sandboat.javautil.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReadPdfUtil {

    /**
     * 提取pdf文本
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


    public static void main(String[] args) throws IOException {

        String s = ReadPdfUtil.extractText("D:\\workspaces\\pdfbox-test\\src\\main\\resources\\告知书.pdf");
        System.out.println(s);
        s = ReadPdfUtil.extractTextByArea(
                "D:\\workspaces\\pdfbox-test\\src\\main\\resources\\告知书.pdf",
                10, 480, 205, 60);
        System.out.println(s);

    }

}
