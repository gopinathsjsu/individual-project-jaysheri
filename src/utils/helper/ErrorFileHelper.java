package utils.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ErrorFileHelper implements OutputFileHelper {
    private ArrayList<String> content;

    @Override
    public void writeToFile(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public void saveFile(Path path) throws IOException {
        FileWriter errorFile = new FileWriter(path.getParent().getParent().toString()+"/OrderOutput/ErrorLog_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".txt");
        for(String line:content)
            errorFile.write(line+"\n");
        errorFile.close();
    }
}
