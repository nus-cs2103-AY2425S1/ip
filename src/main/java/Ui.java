import Task.Task;

import java.util.ArrayList;

public class Ui {

    // GENERIC PRINT STATEMENTS (Can be reused)
    private static void printWithTerminalLines(String message) {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
        System.out.println(message);
        System.out.println(TERMINAL_LINE);
    }

    public void printWelcomeMessage() {
        String introduction = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\n" +
                "How can I purrvide assistance? Purrhaps I could lend a paw!";
        printWithTerminalLines(introduction);
    }

    public void printExitMessage() {
        printWithTerminalLines("Fur-well friend, stay paw-sitive!");
    }

    public void printErrorMessage(String message) {
        printWithTerminalLines("Error: " + message);
    }

    public void printTaskAddedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("(=ↀωↀ=)ノ Task added!\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    public void printTaskDeletedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("Purrr, I've swatted this task away:\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    public void printMarkTaskDoneMessage(Task task) {
        printWithTerminalLines("Meow! I’ve scratched this task off the list!\n" + task);
    }

    public void printMarkTaskUndoneMessage(Task task) {
        printWithTerminalLines("Mrrreow! I’ve batted this task back onto the list.\n" + task);
    }

    public void printListOfTasks(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            printWithTerminalLines("Your task list is as empty as a well-sunned nap spot.");
        } else {
            StringBuilder result = new StringBuilder("Time to stretch those paws and tackle your tasks!\n");
            for (int i = 0; i < taskCount; i++) {
                int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
                result.append(printedIndex).append(".").append(tasks.get(i));
                if (i < taskCount - 1) { // Don't append a newline after the last task
                    result.append("\n");
                }
            }
            printWithTerminalLines(result.toString());
        }
    }
}
