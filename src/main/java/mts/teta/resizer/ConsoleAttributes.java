package mts.teta.resizer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;

@Command(
        synopsisHeading = "%nUsage: ",
        optionListHeading = "Options Settings:%n",
        sortOptions = false,
        separator = " ",
        parameterListHeading = "Parameters:%n"
)
public class ConsoleAttributes {
    @Parameters(index = "0", paramLabel = "input-file", description = "input file")
    protected File inputFile;

    @Option(names = {"--resize"}, arity = "2", description = {"resize the image"}, paramLabel = "width")
    protected Integer[] size;
    
    @Option(names = {"--quality"}, description = {"PEG/PNG compression level"}, paramLabel = "value")
    protected Integer quality;

    @Option(names = {"--crop"}, arity = "4", description = {"@|fg(green) cut|@ out one rectangular area of the image"})
    protected Integer[] crop;

    @Option(names = {"--blur"}, description = {"reduce image noise and reduce detail levels"}, paramLabel = "{radius}")
    protected Integer blurRadius;

    @Option(names = {"--format"}, description = {"output formatted image characteristics"}, paramLabel = "\"output format\"")
    protected String format;

    @Parameters(index = "1", paramLabel = "output-file", description = "output file")
    protected File outputFile;
}
