package thebotfather.util;

import thebotfather.task.Task;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It provides methods to read user input, print output to the console, and display
 * various messages during the execution of the program.
 */
public class Ui {

    /**
     * The Scanner instance used for reading user input from the console.
     */
    Scanner sc;

    /**
     * Constructs a new Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     * @throws TheBotFatherException If no input is detected, prompting the user with an error message.
     */
    public String readCommand() throws TheBotFatherException {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "\tUse \"bye\" if you want to exit the program");
        }
    }

    /**
     * A helper method to print messages with a leading indentation.
     *
     * @param str The message to be printed.
     */
    private void print(String str) {
        System.out.println("    " + str);
    }

    /**
     * Prints a greeting message when the application starts.
     */
    public void printGreeting() {
        this.printLine();
        print("Hello! I'm The BotFather");
        print("I’m gonna make you an offer you can’t refuse.");
        this.printLine();
        this.printHorse();
        this.printLine();
    }

    /**
     * Prints a horizontal line to the console for formatting purposes.
     */
    public void printLine() {
        print("—————————————————————————————————————————————————————————————————");
    }

    /**
     * Prints a message indicating that a new Todo task has been added.
     *
     * @param task The Todo task that was added.
     */
    public void printAddedTodo(Task task) {
        this.print("Leave the gun, take the cannoli.");
        this.print(task.toString());
    }

    /**
     * Prints a message indicating that a new Deadline task has been added.
     *
     * @param task The Deadline task that was added.
     */
    public void printAddedDeadline(Task task) {
        this.print("Look how they massacred my boy.");
        this.print(task.toString());
    }

    /**
     * Prints a message indicating that a new Event task has been added.
     *
     * @param task The Event task that was added.
     */
    public void printAddedEvent(Task task) {
        this.print("That's my family, Kay, that's not me.");
        this.print(task.toString());
    }

    /**
     * Prints the current number of tasks remaining to be done.
     */
    public void printCount() {
        this.print("You have " + TaskList.COUNT + " tasks left to do... think it's doable??");
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskListDesc A description of the tasks in the list.
     */
    public void printTaskList(String taskListDesc) {
        String[] lines = taskListDesc.split("\n");
        for (String line : lines) {
            this.print(line);
        }
        this.printCount();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param taskDesc A description of the task that was marked.
     */
    public void printMarked(String taskDesc) {
        this.print("It will be done");
        this.print(taskDesc);
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param taskDesc A description of the task that was unmarked.
     */
    public void printUnmarked(String taskDesc) {
        this.print("A man who doesn't spend time with his family can never be a real man.");
        this.print(taskDesc);
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public void printDeleted(Task task) {
        this.print("You are sure you wanted to do that right? Anyways... too late");
        this.print(task.toString());
    }

    /**
     * Prints a goodbye message when the application is about to exit.
     */
    public void printGoodBye() {
        print("What are you worried about, if I wanted to kill you, you'd be dead already.");
        sc.close();
    }

    /**
     * Prints an error message when there is an issue loading the tasks from storage.
     */
    public void showLoadingError() {
        print("There seems to have been some error loading the files, check properly");
    }

    /**
     * Prints a custom error message.
     *
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        print(message);
    }

    /**
     * Prints an ASCII art representation of a horse.
     * This is used to add a unique visual element to the application.
     */
    private void printHorse() {
        System.out.println("\t                                 |\\    /|");
        System.out.println("\t                              ___| \\,,/_/");
        System.out.println("\t                           ---__/ \\/    \\");
        System.out.println("\t                          __--/     (D)  \\");
        System.out.println("\t                          _ -/    (_      \\");
        System.out.println("\t                         // /       \\_ /  -\\");
        System.out.println("\t   __-------_____--___--/           / \\_ O o)");
        System.out.println("\t  /                                 /   \\__/");
        System.out.println("\t /                                 /");
        System.out.println("\t||          )                   \\_/\\");
        System.out.println("\t||         /              _      /  |");
        System.out.println("\t| |      /--______      ___\\    /\\  :");
        System.out.println("\t| /   __-  - _/   ------    |  |   \\ \\");
        System.out.println("\t |   -  -   /                | |     \\ )");
        System.out.println("\t |  |   -  |                 | )     | |");
        System.out.println("\t  | |    | |                 | |    | |");
        System.out.println("\t  | |    < |                 | |   |_/");
        System.out.println("\t  < |    /__\\                <  \\");
        System.out.println("\t  /__\\                       /___\\");
    }

}
