package logic.ief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    private FileHandler handler;

    @BeforeEach
    void setUp() {
        handler = new FileHandler();
    }

    @Test
    void testFileGeneration() throws IOException {
        File file = handler.generateFile("./testFile.txt");

        assertTrue(file.exists());
    }

    @Test
    void testIOerror() {
        assertThrows(IOException.class,
                () -> handler.generateFile("fail/bla"));
    }

    @AfterEach
    void tearDown() {
    }

}