package alex;

import alex.storage.Storage;
import alex.tasklist.TaskList;
import alex.ui.Ui;

import java.util.Scanner;

public class Alex {
    private String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String LINE = Ui.LINE;


    public Alex(String filePath) {
        this.filePath = filePath;
        String directoryPath = "./data";
        ui = new Ui();
        storage = new Storage(directoryPath, filePath);
        tasks = new TaskList(storage.loadTasksFromFile(filePath));
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            ui.byeMessage();
        }
        else if (userInput.equalsIgnoreCase("list")) {
            tasks.handleList();
            scan();
        } else if (userInput.startsWith("mark")) {
            tasks.handleMark(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("unmark")) {
            tasks.handleUnmark(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("todo")) {
            tasks.handleTodo(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("deadline")) {
            tasks.handleDeadline(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("event")) {
            tasks.handleEvent(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("delete")) {
            tasks.handleDelete(userInput);
            storage.saveTasksToFile(filePath);
            scan();
        } else if (userInput.startsWith("tasks on")) {
            tasks.handleDate(userInput);
            scan();
        } else if (userInput.startsWith("find")) {
            tasks.handleFind(userInput);
            scan();
        } else {
            System.out.println(LINE);
            System.out.println("Sorry, I don't understand that command. Did you make a typo?");
            System.out.println(LINE);
            scan();
        }
    }

    public static void main(String[] args) {
        Alex alex = new Alex("./data/alex.txt");

        alex.ui.welcomeMessage();

        alex.scan();
    }
}
