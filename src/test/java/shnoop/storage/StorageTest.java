package shnoop.storage;

import org.junit.jupiter.api.Test;
import shnoop.exceptions.EmptyDescriptionException;
import shnoop.exceptions.ImproperFileTypeException;
import shnoop.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class StorageTest {

    @Test
    public void readFileToTask_Todo_success() throws EmptyDescriptionException, ImproperFileTypeException {
        String line = "0001new todotask for me to do! yay";
        assertEquals(new Todo("new todotask for me to do! yay", false),
                Storage.readFileToTask(line));

        String line2 = "1001new todotask for me to do! yay... except i've already done it muahahaha";
        assertEquals(new Todo("new todotask for me to do! yay... except i've already done it muahahaha",
                        true), Storage.readFileToTask(line2));

        String line3 = "1001   this is weird but there's a hole in my heart";
        assertEquals(new Todo("   this is weird but there's a hole in my heart", true),
                Storage.readFileToTask(line3));
    }

    @Test
    public void readFileToTask_invalidFile_exceptionThrown() {
        String line = "2001new todotask for me to do! yay";
        try {
            assertEquals(new Todo("new todotask for me to do! yay", true),
                    Storage.readFileToTask(line));
        } catch (ImproperFileTypeException e) {
            assertEquals("✿ Shnoop ✿: The file is in the wrong format."
                    + " You could try rectifying it or deleting the file. \n", e.getMessage());
        } catch (EmptyDescriptionException eDesc) {
            assertEquals("✿ Shnoop ✿: There must be something in the water."
                    + " Don't leave the description empty! Type something.", eDesc.getMessage());
        }

        String line2 = "2001";
        try {
            assertEquals(new Todo("new todotask for me to do! yay", true),
                    Storage.readFileToTask(line2));
        } catch (ImproperFileTypeException e) {
            assertEquals("✿ Shnoop ✿: The file is in the wrong format."
                    + " You could try rectifying it or deleting the file. \n", e.getMessage());
        } catch (EmptyDescriptionException eDesc) {
            assertEquals("✿ Shnoop ✿: There must be something in the water."
                    + " Don't leave the description empty! Type something.", eDesc.getMessage());
        }

    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}
