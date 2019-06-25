package logic.ief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileHandlerTest {
    private FileHandler handler;

    @BeforeEach
    void setUp() {
        handler = new FileHandler();
    }

    @Test
    void testFileGeneration() {
        File file = handler.generateFile("Bla", "./testFile.txt");

        Assertions.assertTrue(file.exists());
    }

    @AfterEach
    void tearDown() {
    }

}