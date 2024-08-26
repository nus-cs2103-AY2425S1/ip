import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;  
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The Quack chatbot program implements the functionality needed
 * to help users keep track of tasks for them.
 */
public class Quack {

    /** String to print out the spacers between each command */
    public String spacer = "-".repeat(70);
    /** Date time format for printing LocalDateTime objects */
    public DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** The name of the chatbot */
    private String botName = "Quack";
    /** The logo for the chatbot */
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";
    /** Scanner object to take in user inputs */
    private Scanner sc = new Scanner(System.in);
    /** To store all of the users tasks */
    private TaskList toDoList;
    /** Determine if the bot should continue or stop running */
    private boolean isRunning;
    /** All available types of tasks the chatbot supports */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private Storage storage;
    
    /**
     * Creates a Quack chatbot object.
     */
    Quack() {

        this.isRunning = true;
        this.toDoList = new TaskList();
        this.storage = new Storage(this.toDoList);
    }

    /**
     * Prints the logo of Quack.
     */
    private void printLogo() {

        System.out.println(logo + "\n" + spacer);
    }

    /**
     * Prints the greeting message for Quack.
     */
    private void Printgreeting() {

        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    /**
     * Prints the farewell message for Quack.
     */
    private void printFarewell() {
        
        this.isRunning = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Retrieves the task details from input.
     * <p>
     * After retrieveing the task details, create a object and add it into the task list.
     * <p>
     * If the input is invalid an exception will be thrown
     * 
     * @param inputArr Processed input commands by the user.
     * @throws InvalidInputException If there is something wrong with the input provided by the user.
     * @throws InvalidTaskTypeException If the task type given is no supported.
     */
    private void getTaskDetails(String[] inputArr) throws InvalidInputException, InvalidTaskTypeException{

        // Get the task description from the input
        StringBuilder taskDescription = new StringBuilder();

        for (int i = 2; i < inputArr.length; i++) {
            if (taskDescription.length() > 0) {
                taskDescription.append(" ");
            }
            taskDescription.append(inputArr[i]);
        }

        // Get the task type from the input
        String taskType = inputArr[1].toUpperCase();
        
        // Check if the task type is supported by the chatbot 
        boolean correctType = false;

        for (TaskType type : TaskType.values()) {
            if (taskType.equals(type.name())) {
                correctType = true;
                break;
            }
        }

        if (!correctType) {
            throw new InvalidTaskTypeException(taskType);
        }

        // For event or deadline tasks, prompt the user for the start and end dates
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (taskType.equals(TaskType.EVENT.name())) {

            System.out.println("When does this event start? (Format date as : DD/MM/YYYY HH:MM:SS)");
            String startDateString = sc.nextLine();

            try {
                startDate = LocalDateTime.parse(startDateString, this.dateFormat);
                System.out.println(this.spacer);
            } catch (DateTimeParseException dateTimeError){
                System.out.println("Im sorry but the date input is invalid try DD/MM/YYYY HH:MM:SS");
                return;
            }
        }

        if (!taskType.equals(TaskType.TODO.name())) {
            System.out.println("When is this task due? (Format date as : DD/MM/YYYY HH:MM:SS)");
            String endDateString = sc.nextLine();

            try {
                endDate = LocalDateTime.parse(endDateString, this.dateFormat);
                System.out.println(this.spacer);
            } catch (DateTimeParseException dateTimeError){
                System.out.println("Im sorry but the date input is invalid try DD/MM/YYYY HH:MM:SS");
                return;
            }
        }
        
        // // Add the task into the list
        // try {
        //     this.toDoList.addTask(taskDescription.toString(), taskType, startDate, endDate);
        // } catch (InvalidDateTimeException dateTimeError) {
        //     System.out.println(dateTimeError.getMessage());
        // }
        
    }

    /**
     * Executes the command input by the user.
     * <p>
     * Depending on the input given by the user, Quack will execute the command.
     * <p>
     * If the command is not supported by Quack an invalid input exception will be thrown.
     * 
     * @param input The raw input from the user.
     * @throws InvalidInputException If there is something wrong with the input provided by the user.
     */
    private void act (String input) throws InvalidInputException{

        // Process the input by the user
        String[] inputArr = input.split(" ");

        // Extract the command from the input
        String command = inputArr[0].toLowerCase();

        // Depending on the command act accordingly
        switch (command) {
        case "list":
            System.out.println(this.toDoList.toString());
            break;
        case "bye":
            printFarewell();
            break;
        case "add":
            if (inputArr.length < 3) {
                throw new InvalidInputException("Invalid syntax to add a task, try: add <task type> <task description>!");
            }

            try {
                this.getTaskDetails(inputArr);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            break;
        case "mark":
        case "unmark":
            if (inputArr.length < 2) {
                throw new InvalidInputException("Invalid syntax to add a task, try: " + command + " <index of the task>!");
            }

            try {
                this.toDoList.updateTask(Integer.valueOf(inputArr[1]) - 1, command);
            } catch (InvalidIndexException indexErr) {
                System.out.println(indexErr.getMessage());
            }
            break;
        case "delete":
            if (inputArr.length < 2) {
                throw new InvalidInputException("Invalid syntax to add a task, try: delete <index of the task>!");
            }

            try {
                this.toDoList.deleteTask(Integer.valueOf(inputArr[1]) - 1);
            } catch (InvalidIndexException indexErr) {
                System.out.println(indexErr.getMessage());
            }
            break;
        default:
            throw new InvalidInputException("There is no such function as " + command + ", please try again.");
        }

        System.out.println(spacer);
    }

    /**
     * Runs the chatbot and start taking inputs from the user.
     */
    private void run() {

        // Chatbot is running for the first time, display the logo and greet the user
        this.printLogo();
        this.Printgreeting();
        
        // Keep taking inputs from the user as long as the chatbot is running
        while (isRunning) {
            String input = sc.nextLine();
            System.out.println(spacer);

            try {
                act(input);
            } catch (InvalidInputException inputErr) {
                System.out.println(inputErr.getMessage());
                System.out.println(spacer);
            }
        }

        try {
            storage.saveData(this.toDoList);
        } catch (IOException IOErr){
            System.out.println("An error has occured " + IOErr.getMessage());
        }

        // Close the scanner
        sc.close();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
