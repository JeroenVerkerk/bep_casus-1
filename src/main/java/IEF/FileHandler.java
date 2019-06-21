package IEF;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    public File generateFile(String contents, String path) {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
