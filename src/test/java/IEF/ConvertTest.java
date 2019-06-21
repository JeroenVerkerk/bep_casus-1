package IEF;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class ConvertTest {
    private Convert convert;

    @BeforeEach
    void setUp() {
        convert = new Convert();
    }

    @Test
    void testFileGeneration() {
        File file = convert.generateFile("Bla", "./testFile.txt");

        Assertions.assertTrue(file.exists());
    }

    @AfterEach
    void tearDown() {
    }
}