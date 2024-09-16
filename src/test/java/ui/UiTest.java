package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TaskList;

class UiTest {

    private Ui ui;

    @BeforeEach
    void setUp() {
        ui = new Ui();
    }

    @Test
    void testShowWelcome() {
        String expectedOutput = "Hello! I'm Downy.\nHow can I help?\n";
        assertEquals(expectedOutput.trim(), ui.showWelcome().trim());
    }

    @Test
    void testShowExitMessage() {
        String expectedOutput = "Bye! Yippee!";
        assertEquals(expectedOutput.trim(), ui.showExitMessage().trim());
    }

    @Test
    void testShowErrorMessage() {
        String expectedOutput = "Error: An error occurred\n";
        assertEquals(expectedOutput.trim(), Ui.showErrorMessage("An error occurred").trim());
    }

    @Test
    void testShowMessage() {
        String expectedOutput = "This is a message\n";
        assertEquals(expectedOutput.trim(), Ui.showMessage("This is a message").trim());
    }

    @Test
    void testDisplayTasks() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Read a book");
        taskList.addDeadline("Submit report", LocalDateTime.of(2024, 8, 30, 18, 0));

        String expectedOutput = "Here are the tasks in your list:\n"
                + "1. [T] [ ] Read a book\n"
                + "2. [D] [ ] Submit report (by: Aug 30 2024 1800)\n";

        assertEquals(expectedOutput.trim(), ui.displayTasks(taskList).trim());
    }

    @Test
    void testDisplayCompletedTask() {
        Task task = new Task("Read a book");
        String expectedOutput = "Nice! You've completed this task:\n  " + task + "\n";

        assertEquals(expectedOutput.trim(), ui.displayCompletedTask(task).trim());
    }

    @Test
    void testDisplayIncompleteTask() {
        Task task = new Task("Read a book");
        String expectedOutput = "Ok! This task is not complete:\n  " + task + "\n";

        assertEquals(expectedOutput.trim(), ui.displayIncompleteTask(task).trim());
    }

    @Test
    void testDisplayDeletedTask() {
        Task task = new Task("Read a book");
        String expectedOutput = "Ok! This task has been removed:\n  " + task + "\n";

        assertEquals(expectedOutput.trim(), ui.displayDeletedTask(task).trim());
    }

    @Test
    void testDisplayTaskAdded() {
        Task task = new Task("Read a book");
        String expectedOutput = "Okay! Added this task:\n  " + task + "\nNow you have 5 tasks in this list\n";

        assertEquals(expectedOutput.trim(), ui.displayTaskAdded(task, 5).trim());
    }

    @Test
    void testDisplayHelp() {
        String expectedOutput = "Here are a list of valid commands:\n"
                + " - list\n"
                + " - mark <taskNumber>\n"
                + " - unmark <taskNumber>\n"
                + " - delete <taskNumber>\n"
                + " - todo <taskDescription>\n"
                + " - deadline <taskDescription> /by <dueDate>\n"
                + " - event <taskDescription> /from <startTime> /to <endTime>\n"
                + " - bye\n"
                + " - help\n"
                + " - find <keyword>\n"
                + " - note list\n"
                + " - note entry <noteContent>\n"
                + " - note delete <noteNumber>\n";

        assertEquals(expectedOutput.trim(), ui.displayHelp().trim());
    }

    @Test
    void testDisplayMatchingTasksWithMatches() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Read a book");
        taskList.addTodo("Complete the assignment");
        taskList.addTodo("Read the news");

        String expectedOutput = "Here are the tasks in your list that match the keyword:\n"
                + "1. [T] [ ] Read a book\n"
                + "2. [T] [ ] Read the news\n";

        assertEquals(expectedOutput.trim(), ui.displayMatchingTasks(taskList, "read").trim());
    }

    @Test
    void testDisplayMatchingTasksWithoutMatches() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Read a book");
        taskList.addTodo("Complete the assignment");
        taskList.addTodo("Read the news");

        String expectedOutput = "Here are the tasks in your list that match the keyword:\n"
                + "No matching tasks found.\n";

        assertEquals(expectedOutput.trim(), ui.displayMatchingTasks(taskList, "exercise").trim());
    }

    @Test
    void testDisplayMatchingTasksCaseInsensitive() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Read a book");
        taskList.addTodo("Complete the assignment");
        taskList.addTodo("Read the news");

        String expectedOutput = "Here are the tasks in your list that match the keyword:\n"
                + "1. [T] [ ] Read a book\n"
                + "2. [T] [ ] Read the news\n";

        assertEquals(expectedOutput.trim(), ui.displayMatchingTasks(taskList, "READ").trim());
    }

    @Test
    void testDisplayNotes() {
        List<String> notes = new ArrayList<>();
        notes.add("First note");
        notes.add("Second note");

        String expectedOutput = "Here are your notes:\n"
                + "1. First note\n"
                + "2. Second note\n";

        assertEquals(expectedOutput.trim(), ui.displayNotes(notes).trim());
    }

    @Test
    void testDisplayNotesNoNotes() {
        List<String> notes = new ArrayList<>();

        String expectedOutput = "Here are your notes:\nNo notes found.\n";

        assertEquals(expectedOutput.trim(), ui.displayNotes(notes).trim());
    }

    @Test
    void testDisplayNoteAdded() {
        String noteContent = "This is a note";
        String expectedOutput = "Okay! Added this note:\n  This is a note\n";

        assertEquals(expectedOutput.trim(), ui.displayNoteAdded(noteContent).trim());
    }

    @Test
    void testDisplayNoteDeleted() {
        int noteNumber = 2;
        String expectedOutput = "Okay! Deleted note number 2:\n";

        assertEquals(expectedOutput.trim(), ui.displayNoteDeleted(noteNumber).trim());
    }
}
