package br.com.gerdau.reportUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import br.com.gerdau.reportUtils.exception.ReportException;

import java.io.IOException;

public class ReportLogo {

    private Image image;

    public ReportLogo(String path, Float posX, Float posY) {
        setImage(path, posX, posY, null, null);
    }

    public ReportLogo(String path, Float posX, Float posY, Float sizeX, Float sizeY) {
        setImage(path, posX, posY, sizeX, sizeY);
    }

    private void setImage(String path, Float posX, Float posY, Float sizeX, Float sizeY) {
        Image img;
        try {
            img = Image.getInstance(String.format((path)));
        } catch (BadElementException | IOException e) {
            throw new ReportException(e);
        }

        if (sizeX != null && sizeY != null)
            img.scaleToFit(sizeX, sizeY);

        img.setAbsolutePosition(posX, posY);
        setImage(img);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
