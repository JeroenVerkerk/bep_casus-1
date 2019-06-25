package logic.ief;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

    public File generateFile(String path) throws IOException {
        File file = new File(path);
        try {
            file.createNewFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
