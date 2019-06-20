package java.convert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ConverterTest {
    private Converter converter = new Converter();

    @Disabled
    @Test
    public void testConverter(){

    }
    @Disabled
    @AfterEach
    public void tearDown(){
        converter = null;
        assertNull(converter);
    }
}
