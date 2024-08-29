package shrimp.utility;

import shrimp.task.Task;
import shrimp.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void printLogo() {
        String logo = """
                
                       .__          .__               \s
                  _____|  |_________|__| _____ ______ \s
                 /  ___/  |  \\_  __ \\  |/     \\\\____ \\\s
                 \\___ \\|   Y  \\  | \\/  |  Y Y  \\  |_> >
                /____  >___|  /__|  |__|__|_|  /   __/\s
                     \\/     \\/               \\/|__|   \s
                                                      \s
                """;
        System.out.println(AnsiCode.CYAN + logo);
    }

    public void printWelcome() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3";
        System.out.println(AnsiCode.CYAN + greetings);
    }

    public void printExit() {
        String exit = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(exit);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("Gotchaaa~ Here's the list so far:");
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            String output = String.format("    %s.%s", i + 1, task);
            System.out.println(AnsiCode.PURPLE + output + AnsiCode.CYAN);
        }
        printTaskCount(taskList);
    }

    private void printTaskCount(TaskList taskList) {
        System.out.printf("Lemme count~ You now have %s item(s) in your list!%n", taskList.getCount());
    }

    public void printMark(Task task) {
        String output = "heya~ I've checked this task as complete! Feels good, right?";
        System.out.println(output);
        System.out.println("    " + task);
    }

    public void printUnmark(Task task) {
        String output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
        System.out.println(output);
        System.out.println("    " + task);
    }

    public void printDelete(Task task, TaskList taskList) {
        String output = "Done! The task has been deleted!";
        System.out.println(output);
        System.out.println("    " + task);
        printTaskCount(taskList);
    }

    public void printAdd(Task task, TaskList taskList) {
        String output = "rawr! '" + task + "' has been added to the list~";
        System.out.println(output);
        printTaskCount(taskList);
    }

    public void printError(String message) {
        System.out.println(AnsiCode.RED + "Oh nyoo~ " + message);
    }
}
