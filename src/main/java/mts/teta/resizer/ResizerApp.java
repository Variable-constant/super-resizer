package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.ImageProcessor;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(headerHeading = "Version: resizer 0.0.1 https://github.com/Variable-constant/super-resizer",
        name = "resizer",
        mixinStandardHelpOptions = true,
        version = "resizer 0.0.1")
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {
    private Integer resizeWidth;
    private Integer resizeHeight;

    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }
    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        if (size != null) {
            resizeWidth = size[0];
            resizeHeight = size[1];
        }
        ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }

    public void setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public void setResizeWidth(Integer resizeWidth) {
        this.resizeWidth = resizeWidth;
    }

    public void setResizeHeight(Integer resizeHeight) {
        this.resizeHeight = resizeHeight;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public void setCrop(Integer[] crop) {
        this.crop = crop;
    }

    public Integer getBlurRadius() {
        return this.blurRadius;
    }

    public File getInputFile() {
        return this.inputFile;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public Integer getResizeWidth() {
        return this.resizeWidth;
    }

    public Integer getResizeHeight() {
        return this.resizeHeight;
    }

    public Integer getQuality() {
        return this.quality;
    }

    public String getFormat() {
        return this.format;
    }

    public Integer[] getCrop() {
        return this.crop;
    }
}
