package logic.ief;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    public File generateFile(String path) throws IOException {
        File file = new File(path);
        if (!file.createNewFile()) {
            throw new IOException("failed to create file");
        }
        return file;
    }
}
