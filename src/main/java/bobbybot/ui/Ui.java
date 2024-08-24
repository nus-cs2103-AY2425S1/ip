package bobbybot.ui;

import bobbybot.Task;
import bobbybot.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ui {

    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printRemoveTask(TaskList tasks, Task task) {
        printInput(
                "Noted. I've removed this task:",
                "\t" + task,
                "Now you have " + tasks.getSize() + " task(s) in the list."
        );
    }
    public void printAddTask(TaskList tasks, Task task) {
        printInput(
                "Got it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.getSize() + " task(s) in the list."
        );
    }

    /**
     * Prints the tasks that match the search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void listMatchingTasks(TaskList tasks) {
        ArrayList<String> taskListString = new ArrayList<>();
        taskListString.add("Here are the matching tasks in your list:");
        IntStream.
                range(0, tasks.getSize()).
                mapToObj(i -> i + 1 + ". " + tasks.getTask(i)).
                forEach(taskListString::add);
        printInput(taskListString.toArray(String[]::new));
    }

    public void listTasks(TaskList tasks) {
        ArrayList<String> taskListString = new ArrayList<>();
        taskListString.add("Here are the tasks in your list:");
        IntStream.
                range(0, tasks.getSize()).
                mapToObj(i -> i + 1 + ". " + tasks.getTask(i)).
                forEach(taskListString::add);
        printInput(taskListString.toArray(String[]::new));
    }

    public void printInput(String... input) {
        System.out.print("\t");
        printLine();
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.print("\t");
        printLine();
    }

    public void printError(Exception e) {
        printInput(e.getMessage());
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}
