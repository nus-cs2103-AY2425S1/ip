package snowy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void toString_correctInput_success() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("T|1|read book");
        lines.add("D|0|return book|2024-09-05");
        lines.add("E|0|Camp|2024-09-01|2024-09-02");
        TaskList tasks = new TaskList(lines);

        assertEquals("1. [T][X] read book\n" +
                "2. [D][ ] return book (by September 5, 2024)\n" +
                "3. [E][ ] Camp (from September 1, 2024 to: September 2, 2024)\n"
                , tasks.toString());
    }

    @Test
    public void toSaveString_correctInput_success() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("T|1|read book");
        lines.add("D|0|return book|2024-09-05");
        lines.add("E|0|Camp|2024-09-01|2024-09-02");
        TaskList tasks = new TaskList(lines);

        assertEquals("T|1|read book\n" +
                "D|0|return book|2024-09-05\n" +
                "E|0|Camp|2024-09-01|2024-09-02\n"
                , tasks.toSaveString());
    }

    @Test
    public void addToDo_correctInput_success() {
        TaskList tasks = new TaskList();
        tasks.addToDo("Read Book");

        assertEquals("1. [T][ ] Read Book\n", tasks.toString());
    }

    @Test
    public void addToDo_invalidInput_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addToDo("");
            fail();
        } catch (SnowyException e) {
            assertEquals("Invalid input for Todo", e.getMessage());
        }
    }

    @Test
    public void addDeadline_correctInput_success() {
        TaskList tasks = new TaskList();
        tasks.addDeadline("Return Book /by 2024-09-05");

        assertEquals("1. [D][ ] Return Book (by September 5, 2024)\n", tasks.toString());
    }

    @Test
    public void addDeadline_emptyInput_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addDeadline("");
            fail();
        } catch (SnowyException e) {
            assertEquals("Invalid input for Deadline", e.getMessage());
        }
    }

    @Test
    public void addDeadline_missingBy_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addDeadline("Return book 2024-09-05");
            fail();
        } catch (SnowyException e) {
            assertEquals("Invalid input for Deadline", e.getMessage());
        }
    }

    @Test
    public void addDeadline_InvalidDate_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addDeadline("Return book /by 123-09-05");
            fail();
        } catch (SnowyException e) {
            assertEquals("Wrong date format", e.getMessage());
        }
    }


}
