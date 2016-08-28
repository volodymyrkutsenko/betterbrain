package org.straightweb.betterbrain.generating;

import java.math.BigInteger;

public class DocxFileGeneratorConfiguration {

    private BigInteger lineSize;
    private int fontSize;

    public BigInteger getLineSize() {
        return lineSize;
    }

    public void setLineSize(BigInteger lineSize) {
        this.lineSize = lineSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

}
