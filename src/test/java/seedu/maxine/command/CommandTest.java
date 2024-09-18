package seedu.maxine.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.maxine.stubs.StorageStub;
import seedu.maxine.stubs.TaskListStub;
import seedu.maxine.stubs.UiStub;
import seedu.maxine.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private Command command;
    private TaskListStub list;
    @BeforeEach
    public void setUp() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("");
        list = new TaskListStub();
        command = new Command(storage, ui, list);
    }

    @Test
    void testBye() {
        assertEquals("\nBye! I have been maxed out and am going to sleep. " 
                + "Hope to see you again soon!", command.handleBye("bye"));
    }
    @Test
    void testInvalidBye() {
        assertEquals("To say bye to Maxine, just type bye",
                command.handleBye("bye maxine"));
    }

    @Test
    void testList() {
        assertEquals("1. [T][ ] test command class",
                command.handleList("list"));
    }
    @Test
    void testInvalidList() {
        assertEquals("To list things out, just type list",
                command.handleList("list maxine"));
    }

    @Test
    void testMark() {
        assertEquals("Yay! You finally did something today: "
                        + "[T][X] test command class",
                command.handleMark("mark 1"));
    }

    @Test
    void testUnmark() {
        Task task = list.getTask(0);
        task.markUndone();
        assertEquals("Undoing... this task? :'( :[T][ ] test command class",
                command.handleUnmark("unmark 1"));
    }

    @Test
    void testTodo() {
        assertEquals("[T][ ] test command class - todo task added!",
                command.handleTodo("todo test command class"));
    }
    @Test
    void testInvalidTodo() {
        assertEquals("Please follow this format: todo [enter maxine.task]",
                command.handleTodo("todo"));
    }

    @Test
    void testDeadline() {
        assertEquals("[D][ ] finish debugging (by: today) " 
                        + "- deadline task added!",
                command.handleDeadline("deadline finish debugging /by today"));
    }
    @Test
    void testInvalidDeadline() {
        assertEquals("[D][ ] [enter maxine.task] " 
                        + "(by: [enter deadline]) - deadline task added!",
                command.handleDeadline("Please follow this format: " 
                        + "deadline [enter maxine.task] /by [enter deadline]"));
    }

    @Test
    void testEvent() {
        assertEquals("[E][ ] discussion (From: Sep 17 2024 | "
                        + "To: Sep 18 2024) - event added!",
                command.handleEvent("event discussion /from 17-09-2024" 
                        + " /to 18-09-2024"));
    }
    @Test
    void testInvalidEvent() {
        assertEquals("Please follow this format: event [enter event] "
                        + "/from [start date] /to [end date]",
                command.handleEvent("event random words"));
    }

    @Test
    void testDelete() {
        assertEquals("Deleting this task: [T][ ] test command class",
                command.handleDelete("delete 1"));
    }
    @Test
    void testInvalidDelete() {
        assertEquals("follow this format: delete [task no.] or delete all",
                command.handleDelete("delete"));
    }
    @Test
    void testFindValid() {
        assertEquals("Here are the results to your search:\n"
                        + "[T][ ] test command class",
                command.handleFind("find command"));
    }

    @Test
    void testFindInvalid() {
        assertEquals("Oh no! I can't find anything on this...",
                command.handleFind("find fish"));
    }

    @Test
    void handleDeleteAll() {
        assertEquals("All tasks have been deleted!",
                command.handleDeleteAll());
    }

    @Test
    void testGetStatusTrue() {
        assertTrue(command.getStatus(), 
                "Expected the command status to be true but it was false");
    }
    @Test
    void testGetStatusFalse() {
        command.handleBye("bye");
        assertFalse(command.getStatus(),
                "Expected the command status to be false but it was true");
    }
}