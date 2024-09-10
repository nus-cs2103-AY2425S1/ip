package seanbot;

import java.util.List;
import java.util.ArrayList;

import seanbot.Tasks.Task;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showTaskList(TaskList tasks) {
        assert tasks != null : "Tasks cannot be null";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }
    
    public void showFoundTasks(List<Task> foundTasks) {
        assert foundTasks != null : "foundTasks cannot be null";
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + ". " + foundTasks.get(i));
        }
    }

    public static void main(String[] args) {
        // Enable assertions
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

        // Create a Ui instance to test
        Ui ui = new Ui();

        // Create a TaskList for testing
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Test Task 1"));
        taskList.addTask(new Task("Test Task 2"));

        // Test valid case for showTaskList
        try {
            ui.showTaskList(taskList);
            System.out.println("Test 1 Passed: showTaskList (valid case)\n");
        } catch (AssertionError e) {
            System.out.println("Test 1 Failed: showTaskList (valid case)\n");
        }

        // Test null case for showTaskList (should trigger assertion)
        try {
            ui.showTaskList(null);
            System.out.println("Test 2 Failed: showTaskList (null case)\n");
        } catch (AssertionError e) {
            System.out.println("Test 2 Passed: showTaskList (null case)\n");
        }

        // Test valid case for showFoundTasks
        ArrayList<Task> foundTasks = new ArrayList<>();
        foundTasks.add(new Task("Found Task 1"));
        foundTasks.add(new Task("Found Task 2"));

        try {
            ui.showFoundTasks(foundTasks);
            System.out.println("Test 3 Passed: showFoundTasks (valid case)\n");
        } catch (AssertionError e) {
            System.out.println("Test 3 Failed: showFoundTasks (valid case)\n");
        }

        // Test null case for showFoundTasks (should trigger assertion)
        try {
            ui.showFoundTasks(null);
            System.out.println("Test 4 Failed: showFoundTasks (null case)\n");
        } catch (AssertionError e) {
            System.out.println("Test 4 Passed: showFoundTasks (null case)\n");
        }

    }
}
