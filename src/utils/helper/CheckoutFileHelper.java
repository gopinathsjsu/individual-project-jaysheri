package utils.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckoutFileHelper implements OutputFileHelper {
    private ArrayList<String> content;

    @Override
    public void writeToFile(ArrayList<String> content) {
        this.content = content;
    }

    @Override
    public void saveFile(Path path) throws IOException {
        FileWriter checkoutFile = new FileWriter(path.getParent().toString()+"/OrderSuccessfulFile_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".csv");
        for(String line:content)
            checkoutFile.write(line+"\n");
        checkoutFile.close();
    }
}
