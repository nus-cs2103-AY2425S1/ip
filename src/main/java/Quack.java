import java.util.Scanner;

public class Quack {

    // Variables
    private String botName = "Quack";
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";
    
    public String spacer = "-".repeat(50);

    private Scanner sc = new Scanner(System.in);  // Create a Scanner object for the chatbot
    private TaskList toDoList;
    private boolean isRunning;
    
    // Functions
    Quack() {
        this.isRunning = true;
        this.toDoList = new TaskList();
    }

    private void printLogo() {
        System.out.println(logo + "\n" + spacer);
    }

    private void greeting() {
        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    private void farewell() {
        this.isRunning = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void getTaskDetails(String[] inputArr) throws InvalidInputException, InvalidTaskTypeException{

        StringBuilder taskDescription = new StringBuilder();

        for (int i = 2; i < inputArr.length; i++) {
            if (taskDescription.length() > 0) {
                taskDescription.append(" ");
            }
            taskDescription.append(inputArr[i]);
        }

        String taskType = inputArr[1].toLowerCase();
        String startDate = "";
        String endDate = "";

        if (!taskType.equals("todo") && !taskType.equals("event") && !taskType.equals("deadline")) {
            throw new InvalidTaskTypeException(taskType);
        }

        if (taskType.equals("event")) {
            System.out.println("When does this event start?");
            startDate = sc.nextLine();
            if (startDate == "") {
                throw new InvalidInputException("Start date cannot be empty");
            }
        }

        if (!taskType.equals("todo")) {
            System.out.println("When is this task due?");
            endDate = sc.nextLine();
            if (endDate == "") {
                throw new InvalidInputException("End date cannot be empty");
            }
        }

        this.toDoList.addItem(taskDescription.toString(), taskType, startDate, endDate);
    }

    private void act (String input) throws InvalidInputException{
        String[] inputArr = input.split(" ");
        String command = inputArr[0].toLowerCase();

        switch (command) {
            case "list":
                System.out.println(this.toDoList.toString());
                break;

            case "bye":
                farewell();
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

    private void run() {

        // Chatbot is running for the first time, display the logo and greet the user
        this.printLogo();
        this.greeting();
        
        // Keep taking inputs from the user
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
        // Close the scanner since it exited the forloop means the bot has terminated
        sc.close();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
