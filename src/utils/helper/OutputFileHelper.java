package utils.helper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public interface OutputFileHelper {
    void writeToFile(ArrayList<String> content);
    void saveFile(Path path) throws IOException;
}
