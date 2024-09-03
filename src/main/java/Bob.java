import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
    private int counter;

    private String savedFilePath;

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises an instance of Bob.
     */
    public Bob() {
        this.records = new ArrayList<>();
        this.counter = 0;
        this.savedFilePath = "src/main/java/savedFile.txt";
        ui = new Ui();
        storage = new Storage(savedFilePath);
    }


//    public static void main(String[] args)  {
//        String welcome = "Hello! I'm Bob\n"
//                + "\tWhat can I do for you?";
//        Bob.printLines(welcome);
//        Bob bob = new Bob();
//        Bob.chat(bob);
//    }

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> records = storage.loadTaskList();
//        taskList = new TaskList(storage.loadTaskList()); //records are loaded
        if(records == null) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(records); //records are loaded
        }
    }


    public static void main(String[] args) {
        Bob bob = new Bob("src/main/java/data/tasks.txt");
        bob.run();
    }

    void run() {
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
                taskList.addTask(input, inputWords);
            }
            taskList.saveRecords(storage);
            input = scanner.nextLine().trim();
        }
        Ui.printLines("Bye. Hope to see you again soon!");

    }




}
