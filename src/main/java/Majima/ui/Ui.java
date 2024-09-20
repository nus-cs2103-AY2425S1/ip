package Majima.ui;

import Majima.task.Task;
import Majima.task.TaskList;

import java.util.List;

public class Ui {

    private StringBuilder output;

    public Ui() {
        this.output = new StringBuilder();
    }

    public void clearOutput() {
        output.setLength(0); // Clears the output
    }

    public String getOutput() {
        return output.toString(); // Returns the collected output
    }

    /**
     * Runs at the start to greet the user.
     * Integrated with the GUI.
     */
    public String userGreet() {
        clearOutput();
        output.append("KIIIIIRYU-CHAN! It's ya old pal, Majima!\n");
        output.append("What can I do fer ya?\n");
        return output.toString();
    }

    public void showError(String message) {
        output.append("Error: ").append(message).append("\n");
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            output.append("おめでとう, Kiryu-chan! There ain't nothing to do left!\n");
        } else {
            output.append("Here's whatcha gotta do, Kiryu-chan!\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
    }

    public void showTaskAdded(Task task) {
        output.append("Understood, Kiryu-chan! This is goin' into the list: ").append(task).append("\n");
    }

    public void showTaskDeleted(Task task) {
        output.append("Gotcha, Kiryu. Axin' this task off the list: ").append(task).append("\n");
    }

    public void showGoodbye() {
        output.append("Bye bye! Don't keep me waiting fer too long now, ya hear?\n");
    }

    public void showTaskMarked(Task task) {
        output.append("Okay, I've gone ahead and marked that one fer ya.\n").append("  ").append(task).append("\n");
    }

    public void showTaskUnmarked(Task task) {
        output.append("Okay, I've gone ahead and unmarked that one fer ya.\n").append("  ").append(task).append("\n");
    }

    public void showFoundTasks(List<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            output.append("Kiryu! There ain't no tasks matching that description!\n");
        } else {
            output.append("I found these tasks matching yer description:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
    }

    public void showHelp() {
        output.append("Here's the skinny of it, Kiryu-chan!\n\n");
        output.append("Available commands:\n");
        output.append("1. todo <description>: Add a new todo task\n");
        output.append("2. deadline <description> /by <date>: Add a task with a deadline\n");
        output.append("3. event <description> /at <date>: Add an event task\n");
        output.append("4. mark <task_number>: Mark a task as done\n");
        output.append("5. unmark <task_number>: Mark a task as not done\n");
        output.append("6. delete <task_number>: Delete a task\n");
        output.append("7. list: List all tasks\n");
        output.append("8. help: Display this help message\n");
        output.append("9. bye: Exit the application\n");
    }
}
