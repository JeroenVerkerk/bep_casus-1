package logic.ief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FileHandlerTest {
    private FileHandler handler;

    @BeforeEach
    void setUp() {
        handler = new FileHandler();
    }

    @Test
    void testFileGeneration() throws IOException {
        File file = handler.generateFile("Bla", "./testFile.txt");

        Assertions.assertTrue(file.exists());
    }

    @Test
    void testIOerror() {

        Assertions.assertThrows(IOException.class,
                () -> handler.generateFile("bla", "fail/bla"));
    }

    @AfterEach
    void tearDown() {
    }

}