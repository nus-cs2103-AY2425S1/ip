package talker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import talker.task.Deadline;
import talker.task.Event;
import talker.task.PriorityType;

public class ParserTest {

    @Test
    public void parseTaskFromFile_invalidStatus_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseTaskFromFile("T | 0 | test"));
            fail();
        } catch (TalkerException e) {
            assertEquals("Invalid completion tag, corrupted file detected.", e.getMessage());
        }
    }

    @Test
    public void parseTaskFromFile_invalidTodoTask_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseTaskFromFile("T | X | test | test"));
            fail();
        } catch (TalkerException e) {
            assertEquals("Invalid ToDo Task, corrupted file detected.", e.getMessage());
        }
    }

    @Test
    public void parseTaskFromFile_invalidDeadlineTask_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseTaskFromFile("D | X | test | test | test"));
            fail();
        } catch (TalkerException e) {
            assertEquals("Invalid Deadline Task, corrupted file detected.", e.getMessage());
        }
    }

    @Test
    public void parseTaskFromFile_invalidEventTask_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseTaskFromFile("E | X | test | test | test | test"));
            fail();
        } catch (TalkerException e) {
            assertEquals("Invalid Event Task, corrupted file detected.", e.getMessage());
        }
    }

    @Test
    public void parseTaskFromFile_invalidTaskType_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseTaskFromFile("G | X | test"));
            fail();
        } catch (TalkerException e) {
            assertEquals("Invalid task type, corrupted file detected.", e.getMessage());
        }
    }

    @Test
    public void parseTaskFromFile_eventTask_exceptionThrown() throws TalkerException {
        String inputString = "E | X | L | test | 20-12-2024 22:00 | 31-12-2024 22:00";
        assertEquals(new Event("test",
                        "20-12-2024 22:00",
                        "31-12-2024 22:00",
                        true,
                         PriorityType.LOW).toString(),
                Parser.parseTaskFromFile(inputString).toString());
    }

    @Test
    public void parseTaskFromFile_deadlineTask_exceptionThrown() throws TalkerException {
        String inputString = "D | X | L | test | 20-12-2024 22:00";
        assertEquals(new Deadline("test",
                        "20-12-2024 22:00",
                        true,
                         PriorityType.LOW).toString(),
                Parser.parseTaskFromFile(inputString).toString());
    }

}
