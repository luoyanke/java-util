package com.sandboat.javautil.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ReadPdfUtil {


    public static String extractText(String filePath)  throws IOException{
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


    public static void main(String[] args) throws IOException {

        String s = ReadPdfUtil.extractText("D:\\workspaces\\pdfbox-test\\src\\main\\resources\\告知书.pdf");
        System.out.println(s);

    }

}
