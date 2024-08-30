package quack;

import java.util.Scanner;
import quack.tasks.Task;
import quack.exception.InvalidIndexException;
import quack.exception.InvalidTaskTypeException;

/**
 * This class is responsible for displaying any UI interface,
 * for the Quack chatbot.
 */
public class Ui {
    
    /** String to print out the spacers between each command */
    private String spacer = "-".repeat(65);
    /** The name of the chatbot */
    private String botName = "Quack";
    /** The logo for Quack */
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";

    /** Farewell message for Quack */
    private String farewellMessage = "Bye. Hope to see you again soon!";
    /** Greeting message for Quack */
    private String greetingMessage = "Hello! I'm " + this.botName + "\nWhat can I do for you?\n" + this.spacer;
    /** Scanner to read user inputs*/
    private Scanner scanner = new Scanner(System.in);
    /** A list of all possible task types */
    private enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }
    
    /**
     * Creates a Ui object.
     */
    public Ui () {

    }

    /**
     * Closes the scanner object.
     */
    public void closeScanner() {
    
        this.scanner.close();
    }

    /**
     * Prints the logo of Quack.
     */
    public void printLogo() {

        System.out.println(this.logo + "\n" + this.spacer);
    }

    /**
     * Prints the greeting message for Quack.
     */
    public void printgreeting() {

        this.printLogo();
        System.out.println(this.greetingMessage);
    }

    /**
     * Prints the farewell message for Quack.
     */
    public void printFarewell() {
    
        System.out.println(this.farewellMessage);
    }

    /**
     * Prints the spacer UI element.
     */
    public void printSpacer() {

        System.out.println(this.spacer);
    }

    /**
     * Prints the spacer UI element.
     * @param err The exception that was catched by Quack.
     */
    public void printExceptionMessage(Exception err) {

        System.out.println(err.getMessage() + "\n" + this.spacer);
    }

    /**
     * Prints the object in its string representation.
     * @param obj Object to be printed.
     */
    public void printObject(Object obj) {

        System.out.println(obj.toString() + "\n" + this.spacer);
       
    }

    /**
     * Prints a comfirmation message to the user once a task is updaed.
     * @param task The task that has been modified.
     * @param command The command that the user entered.
     * @param taskList A list that stores all the tasks tracked by Quack.
     */
    public void printUpdateSuccessfulMessage(Task task, String command, TaskList taskList) {
        
        System.out.println("Success! I have " + command + "ed this task: " + task.toString() + "\n"+
                            "You now have " + taskList.getLength() + " tasks in your list right now!\n" + this.spacer);
    }

    /**
     * Requests the user to input a command.
     * @return A string representation of the command the user entered.
     */
    public String requestUserCommand() {

        System.out.print("What would you like me to do next: ");
        String input = this.scanner.nextLine();
        System.out.println(this.spacer);
        return input;
    }

    /**
     * Requests the user to provide a index input.
     * @param command The command the user has entered.
     * @return The index the user entered as an integer.
     * @throws InvalidIndexException If the index entered is invalid.
     */
    public int requestIndexFromUser(String command) throws InvalidIndexException{
        
        String input = null;
        try {
            System.out.println("Which task do you want to " + command + "? (Input the index of the task): ");
            input = this.scanner.nextLine();
            int index = Integer.parseInt(input);
            System.out.println(this.spacer);
            return index;
        } catch (NumberFormatException numFormatError){
            throw new InvalidIndexException(input);
        }
    }

    /**
     * Requests the user to input a task type.
     * @return A string representation of the task type the user entered.
     * @throws InvalidTaskTypeException If the user inputs a invalid task type.
     */
    public String requestTaskType() throws InvalidTaskTypeException{

        System.out.println("What is the type of task you would like to add: ");
        String input = this.scanner.nextLine();
        System.out.println(this.spacer);

        try {
            this.checkTaskType(input);
            return input;
        } catch (InvalidTaskTypeException taskTypeError) {
            throw taskTypeError;
        }
    }

    /**
     * Requests the user to input a task description.
     * @param taskType The task type the user has entered.
     * @return A string representation of the task description the user entered.
     */
    public String requestTaskDescription(String taskType) {

        System.out.println("What is the description for the " + taskType + " task: ");
        String input = this.scanner.nextLine();
        System.out.println(this.spacer);
        return input;
       
    }

    /**
     * Requests the user to input a task description.
     * @param taskType The task type the user has entered.
     * @return A string representation of the task description the user entered.
     */
    public String requestStartDate(String taskType) {

        System.out.println("When is the start date for the " + taskType + " task (Format: DD/MM/YYYY HH:MM:SS): ");
        String input = this.scanner.nextLine();
        System.out.println(this.spacer);
        return input;
    }

    /**
     * Requests the user to input a task description.
     * @param taskType The task type the user has entered.
     * @return A string representation of the task description the user entered.
     */
    public String requestEndDate(String taskType) {

        System.out.println("When is the end date for the " + taskType + " task (Format: DD/MM/YYYY HH:MM:SS): ");
        String input = this.scanner.nextLine();
        System.out.println(this.spacer);
        return input;
       
    }

    /**
     * Checks if the task type given by the user is a valid one.
     * @param taskType The type of tasks to be created.
     * @throws InvalidTaskTypeException If the user inputs a invalid task type.
     */
    private void checkTaskType(String taskType) throws InvalidTaskTypeException{
        String upperCasedTaskType = taskType.toUpperCase();
        for (TaskTypes tasktypes : TaskTypes.values()) {
            if (tasktypes.name().equals(upperCasedTaskType)) {
                return;
            }
        }
        throw new InvalidTaskTypeException(taskType);
    }

}
