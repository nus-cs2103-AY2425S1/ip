package Bellroy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
public class BellroyTest {
    private static final String testPath = "Bellroy_Test.txt";

    @BeforeEach
    public void setUp() {
        File file = new File(testPath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testFileCreation() {
        new Bellroy(testPath);
        File file = new File(testPath);
        assertTrue(file.exists());
    }

    @AfterEach
    public void cleanUp() {
        File file = new File(testPath);
        if (file.exists()) {
            file.delete();
        }
    }
}
