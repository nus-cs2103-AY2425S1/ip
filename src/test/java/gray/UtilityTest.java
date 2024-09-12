package gray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.junit.jupiter.api.Test;

public class UtilityTest {
    @Test
    public void serialise_badFile_exceptionThrown() {
        try {
            Utility.serialize(new File("./awd/awd/awd"), new Serializable() {});
            fail();
        } catch (IOException e) {
            assertEquals("./awd/awd/awd (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void deserialise_badFile_exceptionThrown() {
        try {
            Utility.deserialize(new File("./awd/awd/awd"));
            fail();
        } catch (IOException e) {
            assertEquals("./awd/awd/awd (No such file or directory)", e.getMessage());
        } catch (ClassNotFoundException e) {
            fail();
        }
    }
}
