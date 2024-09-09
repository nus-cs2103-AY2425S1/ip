package BottleOpener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Tasklist tasklist;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    void setUp() {
        tasklist = new Tasklist();
        ui = new Ui("TestBot");
    }

    @Test
    void testExecute_byeCommand() {
        parser = new Parser("bye", tasklist, ui);
        parser.execute();
        assertTrue(parser.checkExit());
    }

    @Test
    void testExecute_listCommand_emptyList() {
        parser = new Parser("list", tasklist, ui);
        parser.execute();
        assertEquals(0, tasklist.getSize());
    }

    @Test
    void testExecute_deleteCommand_validIndex() {
        Task task = new ToDo("Test Task");
        tasklist.addTask(task);
        parser = new Parser("delete 1", tasklist, ui);
        parser.execute();
        assertEquals(0, tasklist.getSize());
    }

    @Test
    void testExecute_todoCommand() {
        parser = new Parser("todo Test ToDo", tasklist, ui);
        parser.execute();
        assertEquals(1, tasklist.getSize());
        assertEquals("Test ToDo", tasklist.getTask(0).getDescription());
    }

    @Test
    void testExecute_deadlineCommand_validInput() {
        parser = new Parser("deadline Submit report /by 31/12/2023", tasklist, ui);
        parser.execute();
        assertEquals(1, tasklist.getSize());
        assertInstanceOf(Deadline.class, tasklist.getTask(0));
    }

    @Test
    void testExecute_eventCommand_validInput() {
        parser = new Parser("event Meeting /from 31/12/2023 /to 01/01/2024", tasklist, ui);
        parser.execute();
        assertEquals(1, tasklist.getSize());
        assertInstanceOf(Event.class, tasklist.getTask(0));
    }

    @Test
    void testExecute_invalidCommand() {
        parser = new Parser("invalid command", tasklist, ui);
        parser.execute();
        assertEquals(0, tasklist.getSize()); // No tasks should be added
    }

}
