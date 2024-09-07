package socchat;

import java.util.Scanner;

/**
 * Represents the main class for the Socchat application.
 * This class handles user interaction, manages tasks, and processes commands.
 */
public class Socchat {

    private static Scanner scanner = new Scanner(System.in);
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;


    /**
     * Constructs a Socchat instance with the specified file path.
     * Initializes the user interface, storage, and task list.
     *
     */
    public Socchat() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            taskList = new TaskList(Storage.load());
        } catch (SocchatException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat mess age.
     */
    public String getResponse(String input) {
        parser = new Parser(taskList);
        String[] strToken = Parser.processInput(input);
        return parser.getResponse(strToken);
    }

//    /**
//     * Starts the Socchat application and enters the main command loop.
//     * Processes user input and executes commands based on the input.
//     */
//    public void run() {
//        greet();
//
//        chatLoop:
//        while (true) {
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//        new Socchat().run();
//    }
//
//

    /**
     * Prints a greeting message to the user.
     */
    public static String greet() {
        String respond = "";
        respond += ("Hello! I'm Socchat!\n");
        respond += ("What can I do for you?\n");
        return respond;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }



}

