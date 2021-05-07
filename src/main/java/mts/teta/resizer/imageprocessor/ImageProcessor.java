package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.ResizerApp;

import net.coobird.thumbnailator.Thumbnails;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    public void processImage(BufferedImage img, ResizerApp resizerApp) throws Exception {
        if (resizerApp.getResizeWidth() == null || resizerApp.getResizeHeight() == null) {
            throw new BadAttributesException("Please check params!");
        }

        MarvinImage marvinImage = new MarvinImage();
        marvinImage.setBufferedImage(img);
        if (resizerApp.getBlurRadius() != null) {
            MarvinPluginCollection.gaussianBlur(marvinImage.clone(), marvinImage, resizerApp.getBlurRadius());
        }

        if (resizerApp.getCrop() != null) {
            Integer[] crop = resizerApp.getCrop();
            MarvinPluginCollection.crop(marvinImage.clone(), marvinImage, crop[2], crop[3], crop[0], crop[1]);
        }

        marvinImage.update();
        img = marvinImage.getBufferedImage();

        Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(img);
        builder.forceSize(resizerApp.getResizeWidth(), resizerApp.getResizeHeight());
        if (resizerApp.getFormat() != null) {
            if (!isFormatAllowed(resizerApp)) {
                throw new BadAttributesException("This format is now allowed");
            }
            builder.outputFormat(resizerApp.getFormat());
        }

        if (resizerApp.getQuality() != null) {
            if (resizerApp.getQuality() < 1 || resizerApp.getQuality() > 100) {
                throw new BadAttributesException("Quality should be an integer from 1 to 100");
            }
            builder.outputQuality(resizerApp.getQuality()/100.0);
        }

        builder.toFile(resizerApp.getOutputFile());
    }

    private boolean isFormatAllowed(ResizerApp resizerApp) {
        String[] allowedFormats = ImageIO.getWriterFormatNames();
        for (String s : allowedFormats) {
            if (resizerApp.getFormat().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
