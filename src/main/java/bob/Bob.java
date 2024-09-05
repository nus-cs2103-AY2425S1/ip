package bob;

import java.util.ArrayList;
import java.util.Scanner;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;


/**
 * This is a chatBot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises an instance of the chatBot Bob.
     * Bob's ui, storage and taskList is also initialised.
     *
     * @param filePath Relative file path of where the records will be saved.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> records = storage.loadTaskList();
        if (records == null) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(records); //records are loaded
        }
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        Bob bob = new Bob("src/main/java/bob/data/tasks.txt");
        bob.run();
    }

    /**
     * Starts the main program for the chatBot.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim(); //input with NO whitespace in front/back

        while (!input.equals("bye")) {
            String[] inputWords = input.split("\s+");
            String keyword = inputWords[0];

            switch (keyword) {
            case "list":
                taskList.listRecords();
                break;
            case "mark":
                taskList.updateMark(input, inputWords, true);
                break;
            case "unmark":
                taskList.updateMark(input, inputWords, false);
                break;
            case "delete":
                taskList.delete(input);
                break;
            case "event":
                taskList.addTask(input, inputWords);
                break;
            case "deadline":
                taskList.addTask(input, inputWords);
                break;
            case "todo":
                taskList.addTask(input, inputWords);
                break;
            default:
                System.out.println("Please enter a valid keyword");
            }
            taskList.saveRecords(storage);
            input = scanner.nextLine().trim();
        }
        Ui.printLines("Bye. Hope to see you again soon!");
    }
}
