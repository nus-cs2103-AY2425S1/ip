package gray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class UtilityTest {
    @Test
    public void serialise_badFile_exceptionThrown() {
        try {
            Utility.serialise(new File("./awd/awd/awd"), new TaskList());
            fail();
        } catch (IOException e) {
            assertEquals("./awd/awd/awd (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void deserialise_badFile_exceptionThrown() {
        try {
            Utility.deserialise(new File("./awd/awd/awd"));
            fail();
        } catch (IOException e) {
            assertEquals("./awd/awd/awd (No such file or directory)", e.getMessage());
        } catch (ClassNotFoundException e) {
            fail();
        }
    }
}
