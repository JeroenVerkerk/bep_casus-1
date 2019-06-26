package logic.ief;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    public File generateFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
