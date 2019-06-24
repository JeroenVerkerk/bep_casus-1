package ief;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {

    public File generateFile(String contents, String path) {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter out = new PrintWriter(path);
            out.println(contents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
