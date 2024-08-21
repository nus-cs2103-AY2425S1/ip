import java.util.Scanner;

/**
* The Quack chatbot program implements the functionality needed
* to help users keep track of tasks for them.
*/
public class Quack {

    /** String to print out the spacers between each command */
    public String spacer = "-".repeat(50);
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
    
    /**
     * Constructor to create a Quack chatbot object.
     */
    Quack() {

        this.isRunning = true;
        this.toDoList = new TaskList();
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
     * Get task details from input.
     * Create a task object and add it into the task list.
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
        String startDate = "";
        String endDate = "";

        if (taskType.equals(TaskType.EVENT.name())) {
            System.out.println("When does this event start?");
            startDate = sc.nextLine();
            if (startDate == "") {
                throw new InvalidInputException("Start date cannot be empty");
            }
        }

        if (!taskType.equals(TaskType.TODO.name())) {
            System.out.println("When is this task due?");
            endDate = sc.nextLine();
            if (endDate == "") {
                throw new InvalidInputException("End date cannot be empty");
            }
        }
        
        // Add the task into the list
        this.toDoList.addTask(taskDescription.toString(), taskType, startDate, endDate);
    }

    /**
     * Depending on the input given by the user, Quack will execute the command,
     * given that the Quack supports the command
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
     * Function to run the chatbot and start taking inputs from the user.
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

        // Close the scanner
        sc.close();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
