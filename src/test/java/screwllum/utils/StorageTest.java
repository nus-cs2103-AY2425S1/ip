package screwllum.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import screwllum.exception.IllegalFileFormatException;
import screwllum.tasks.Task;

public class StorageTest {
    class TaskStub extends Task {
        private String output;
        TaskStub(String output) {
            super(null);
            this.output = output;
        }
        @Override
        public String toSaveFormat() {
            return "";
        }

        @Override
        public String toString() {
            return output;
        }
    }
    @Test
    public void load_valid_success() throws IllegalFileFormatException {
        Storage storage = new Storage();

        Task todo = new TaskStub("[T][ ] test");
        assertEquals(todo.toString(), storage.parseTask("T_0_test").toString());

        Task deadline = new TaskStub("[D][ ] hw (by: May 04 2022)");
        assertEquals(deadline.toString(), storage.parseTask("D_0_hw_2022-5-4").toString());

        Task event = new TaskStub("[E][ ] surprise (from: May 04 2010 to: Sep 09 1999)");
        assertEquals(event.toString(), storage.parseTask("E_0_surprise_2010-5-4_1999-9-9").toString());
    }

    @Test
    public void load_invalid_exceptionThrown() throws IllegalFileFormatException {
        Storage storage = new Storage();
        String savedData = "A_0_test";
        try {
            Task todo = new TaskStub("[T][ ] test");
            assertEquals(todo.toString(), storage.parseTask(savedData).toString());
            fail();
        } catch (Exception e) {
            String expected = "How peculiar, the file being loaded contains "
                    + savedData
                    + " which is not the right format.\n"
                    + "Please delete the existing file so that I can write a new one for you!";
            assertEquals(expected, e.getMessage());
        }
    }
}
