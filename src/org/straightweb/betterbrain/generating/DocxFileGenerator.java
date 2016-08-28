package org.straightweb.betterbrain.generating;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class DocxFileGenerator {

    public static void generateDocxFile(String fileName, String text, DocxFileGeneratorConfiguration fileConfiguration) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();

        setSingleLineSpacing(paragraph, fileConfiguration);

        XWPFRun paragraphRun = paragraph.createRun();

        addTextToDocxRun(paragraphRun, text);

        paragraphRun.setFontSize(fileConfiguration.getFontSize());
        try {
            document.write(new FileOutputStream(new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTextToDocxRun(XWPFRun run, String data) {
        if (data.contains("\n")) {
            String[] lines = data.split("\n");
            run.setText(lines[0], 0); // set first line into XWPFRun
            for (int i = 1; i < lines.length; i++) {
                // add break and insert new text
                run.addBreak();
                run.setText(lines[i]);
            }
        } else {
            run.setText(data, 0);
        }
    }

    private static void setSingleLineSpacing(XWPFParagraph para, DocxFileGeneratorConfiguration fileConfiguration) {
        CTPPr ppr = para.getCTP().getPPr();
        if (ppr == null) {
            ppr = para.getCTP().addNewPPr();
        }
        CTSpacing spacing = ppr.isSetSpacing() ? ppr.getSpacing() : ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(fileConfiguration.getLineSize());
    }
}
