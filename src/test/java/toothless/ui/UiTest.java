package toothless.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import toothless.task.Task;
import toothless.task.ToDo;

/**
 * Tests for Ui class.
 */
public class UiTest {

    @Test
    public void testWelcome() {
        String expectedOutput = "Welcome to the dragon's den!\n"
                + "Greetings, Dragon Rider!\n\n"
                + "I'm Toothless, your friendly dragon companion.\n"
                + "What adventure shall we embark on today?\n\n"
                + "Enter 'help' to see the list of commands.\n\n";
        assertEquals(expectedOutput, Ui.welcome());
    }

    @Test
    public void testGreeting() {
        String expectedOutput = "Hello! I'm Toothless\nHow can I help today dragon rider?\n\n";
        assertEquals(expectedOutput, Ui.greeting());
    }

    @Test
    public void testBye() {
        String expectedOutput = "Until next time, dragon rider!\n"
                + "Toothless the Night Fury, signing off.\n"
                + "See you soon!\n\n"
                + "(The input is disabled, restart to chat with Toothless again!)\n\n";
        assertEquals(expectedOutput, Ui.bye());
    }

    @Test
    public void testAddTaskMessage() {
        Ui ui = new Ui();
        Task task = new ToDo("Test task");
        String expectedOutput = "Your task\n\t\t"
                + task + "\nadded to the quest board!\n\n"
                + "Now there is 1 quests in your quest board.\n\n";
        assertEquals(expectedOutput, ui.addTaskMessage(task, 1));
    }

    @Test
    public void testUnknownCommand() {
        Ui ui = new Ui();
        String expectedOutput = "I'm sorry, I do not understand what you mean.\n"
                + "Please enter a valid command.\n\n";
        assertEquals(expectedOutput, ui.unknownCommand());
    }

    @Test
    public void testMarkDoneMessage() {
        Ui ui = new Ui();
        Task task = new ToDo("Test task");
        String expectedOutput = "Good job! You had completed this quest!\n"
                + task + "\n\n";
        assertEquals(expectedOutput, ui.markDoneMessage(task));
    }

    @Test
    public void testMarkUndoneMessage() {
        Ui ui = new Ui();
        Task task = new ToDo("Test task");
        String expectedOutput = "Oops! Quest is back in play!\n"
                + task + "\n\n";
        assertEquals(expectedOutput, ui.markUndoneMessage(task));
    }

    @Test
    public void testHelpMessage() {
        Ui ui = new Ui();
        String expectedOutput = "Here are the list of commands you can use:\n"
                + "Category A: Adding tasks\n"
                + "1. Todo task: todo <description>\n"
                + "2. Deadline task: deadline <description> /by <deadline>\n"
                + "3. Event Task: event <description> /from <start time> /to <end time>\n\n"
                + "Category B: Managing tasks\n"
                + "4. List all tasks: list\n"
                + "5. Mark a task as done: mark <index>e\n"
                + "6. Marks a task as undone: unmark <index>\n"
                + "7. Delete a task based on index: delete <index>\n"
                + "8. Find the task with the keyword: find <keyword>\n\n"
                + "Category C: Other commands\n"
                + "9. Shows the list of commands: help\n"
                + "10. Exit the program: bye\n\n";
        assertEquals(expectedOutput, ui.helpMessage());
    }
}
