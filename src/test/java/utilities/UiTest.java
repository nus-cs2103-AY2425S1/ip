package utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import utilities.TaskList;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    private Ui ui;
    private Task task1;
    private Task task2;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        task1 = new Todo("Finish homework");
        task2 = new Todo("Buy groceries");
        taskList = new TaskList();
        taskList.add(task1);
        taskList.add(task2);
    }

    @Test
    public void testShowTaskAdded() {
        String expected = "Got it, girl. I've added this task\u2728:\n " + task1 + "\n Now you have 2 tasks in the list.";
        String actual = ui.showTaskAdded(task1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testHelloResponse() {
        String expected = "Aww... no one ever really greets me! Thanks honey, you're too sweet. Now let's get stuff done, girl\u2728!";
        String actual = ui.helloResponse();
        assertEquals(expected, actual);
    }

    @Test
    public void testRudeResponse() {
        String expected = "Girl! That's kinda mean :(." + " I just wanted to help...";
        String actual = ui.rudeResponse();
        assertEquals(expected, actual);
    }

    @Test
    public void testShowTaskRemoved() {
        taskList.remove(0);
        String expected = "Gotcha, girl. I've removed this task\u2728:\n " + task1 + "\n Now you have 1 tasks in the list.";
        String actual = ui.showTaskRemoved(task1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShowTaskList() {
        String expected = "Here's your list, honey\u2728:\n 1." + task1 + "\n 2." + task2 + "\n";
        String actual = ui.showTaskList(taskList);
        assertEquals(expected, actual);
    }

    @Test
    public void testShowTaskMarked() {
        String expected = "Good job, girboss! I've marked this task as done\u2728:\n " + task1;
        String actual = ui.showTaskMarked(task1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShowTaskUnmarked() {
        String expected = "Aww, I've unmarked this task\u2728:\n " + task1;
        String actual = ui.showTaskUnmarked(task1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShowMatchingTasks() {
        TaskList matchingTasks = new TaskList();
        matchingTasks.add(task1);

        String expected = "Here's the matching tasks in your list, girl\u2728:\n 1." + task1 + "\nNow you have 2 tasks in the list.";
        String actual = ui.showMatchingTasks(matchingTasks, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testShowHelp() {
        String expected = "Here are the available commands, honey:\n" +
                "\u2728 list - Display all your tasks.\n" +
                "\u2728 mark <task number> - Mark a task as done.\n" +
                "\u2728 unmark <task number> - Unmark a task as not done.\n" +
                "\u2728 todo <description> - Add a new todo task.\n" +
                "\u2728 deadline <description> /by <M/d/yyyy HHmm> - Add a new deadline task.\n" +
                "\u2728 do <description> /after <M/d/yyyy HHmm> - Add a task to do after a certain time.\n" +
                "\u2728 event <description> /from <M/d/yyyy HHmm> /to <M/d/yyyy HHmm> - Add a new event.\n" +
                "\u2728 delete <task number> - Delete a task.\n" +
                "\u2728 find <keyword> - Find tasks with a keyword.\n" +
                "\u2728 bye - Exit the chatbot.";
        String actual = ui.showHelp();
        assertEquals(expected, actual);
    }
}
