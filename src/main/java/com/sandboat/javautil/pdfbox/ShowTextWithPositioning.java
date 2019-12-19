/*
 * Copyright 2017 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sandboat.javautil.pdfbox;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This example shows how to justify a string using the showTextWithPositioning method. First only
 * spaces are adjusted, and then every letter.
 *
 * @author Dan Fickling
 */
public class ShowTextWithPositioning
{
    private static final float FONT_SIZE = 20.0f;

    private ShowTextWithPositioning()
    {
    }

    public static void main(String[] args) throws IOException
    {
        doIt("yyyyy", "justify-example.pdf");
    }

    public static void doIt(String message, String outfile) throws IOException
    {
        // the document

        try (PDDocument doc = new PDDocument();){
             InputStream is =ShowTextWithPositioning.class.getClassLoader().getResourceAsStream("simkai.ttf");
            // Page 1
            PDFont font = PDTrueTypeFont.load(doc, is, Encoding.getInstance(COSName.WIN_ANSI_ENCODING));
            //PDFont font = PDType1Font.loa(doc, is);
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            // Get the non-justified string width in text space units.
            float stringWidth = font.getStringWidth(message) * FONT_SIZE;

            // Get the string height in text space units.
            float stringHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() * FONT_SIZE;

            // Get the width we have to justify in.
            PDRectangle pageSize = page.getMediaBox();

            try (PDPageContentStream contentStream = new PDPageContentStream(doc,
                    page, AppendMode.OVERWRITE, false))
            {
                contentStream.beginText();
                contentStream.setFont(font, FONT_SIZE);
                
                // Start at top of page.
                contentStream.setTextMatrix(
                        Matrix.getTranslateInstance(50, pageSize.getHeight() - stringHeight / 1000f));
                
                // First show non-justified.
                contentStream.showText(message);
                
                // Move to next line.
                contentStream.setTextMatrix(
                        Matrix.getTranslateInstance(0, pageSize.getHeight() - stringHeight / 1000f * 2));
                contentStream.showText("哈哈哈");


                // Finish up.
                contentStream.endText();
            }

            doc.save(outfile);
        }
    }
}
