import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SecondMind {
    private static final String line = "____________________________________________________________";
    private static final String logo = "SecondMind";
    private static final String DATA_FILE_PATH = "../../../SecondMind.txt";
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public SecondMind() {
        storage = new Storage(DATA_FILE_PATH);
        taskList = new TaskList(storage.loadTaskList());
        parser = new Parser();
    }

    private void printLineSeparator() {
        System.out.println(line);
    }

    private void printErrorMessage(Exception e) {
        printLineSeparator();
        System.out.println(e);
        printLineSeparator();
    }

    private void greetUser() {
        printLineSeparator();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private void execute(String[] instruction) {
        String command = instruction[0];
        if (command.equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsDone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, true, taskList.getTaskCount());
                printLineSeparator();
                System.out.println("Well done! You have completed the following task:");
                System.out.println(taskList.getTask(taskNumber));
                printLineSeparator();
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskList.getTaskCount() + " tasks in your task list.");
                printLineSeparator();
                return;
            }
        } else if (command.equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsUndone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, false, taskList.getTaskCount());
                printLineSeparator();
                System.out.println("I've marked the following task as incomplete:");
                System.out.println(taskList.getTask(taskNumber));
                printLineSeparator();
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskList.getTaskCount() + " tasks in your task list.");
                printLineSeparator();
                return;
            }
        } else if (command.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                storage.delete(taskNumber, taskList.getTaskCount());
                printLineSeparator();
                System.out.println("I've removed the following task:");
                System.out.println("\t" + taskList.getTask(taskNumber));
                System.out.println("You have a grand total of " + taskList.getTaskCount() + " task(s)");
                printLineSeparator();
                taskList.delete(taskNumber);
            } catch (InvalidTaskNumberException e) {
                printLineSeparator();
                System.out.println(e);
                System.out.println("There are " + taskList.getTaskCount() + " tasks in your task list.");
                printLineSeparator();
            }
        } else if (command.equals("list")) {
            taskList.printTaskList();
        } else {
            Task curr = taskList.addToTaskList(instruction[1]);
            if (curr == null) {
                return;
            } else {
                try {
                    storage.appendToFile(curr.getStorageRepresentation(), taskList.getTaskCount());
                } catch (IOException e) {
                    printErrorMessage(e);
                }
            }
        }
    }

    private void run() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String[] instruction = parser.processInput(reader.nextLine());
            if (instruction[0].equals("bye")) {
                break;
            } else {
                execute(instruction);
            }
        }
        reader.close();
    }

    private void exitProgram() {
        printLineSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void main(String[] args) {
        SecondMind sm = new SecondMind();
        sm.greetUser();
        sm.run();
        sm.exitProgram();
    }
}
