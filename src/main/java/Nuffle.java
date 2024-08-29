import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Nuffle {
    private static Storage storage = new Storage("./data/Nuffle.txt");

    private static ArrayList<Task> inputList;

    static {
        try {
            inputList = storage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printLine() {
        /**
         * This method will print out a border
         */
        // This method will be used to print the border
        System.out.println("---------------------------------------------");
    }

    private static void outputList() {
        /**
         * This method will print out all the task that is stored in the inputList
         */

        // If there is no user input in the list
        if (inputList.isEmpty()) {
            System.out.println("List is empty. No input added.");
        } else {
            // Iterate over the list to print out all the user inputs
            for (int index = 0; index < inputList.size(); index++) {
                System.out.println("" + (index + 1) + ". " + inputList.get(index));
            }
        }
    }

    private static void markTask(int index) {
        /**
         * Marks a specified task based on the given index
         *
         * @param index the index of the task to mark
         */
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.size()) {
            Task currTask = inputList.get(index);
            currTask.markAsDone();
            printLine();
            System.out.println("Nice! I have marked this task as done!");
            System.out.println(" " + currTask);
            printLine();
        } else {
            // print as error message
            printLine();
            System.out.println("Opps! There appears to be an index error!");
            printLine();
        }
    }
    private static void unMarkTask(int index) {
        /**
         * Unmarks a specified task based on the given index
         *
         * @param index the index of the task to unmark
         */
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.size()) {
            Task currTask = inputList.get(index);
            currTask.markNotDone();
            printLine();
            System.out.println("OK! I have marked this task as not done yet.");
            System.out.println(" " + currTask);
            printLine();
        } else {
            // print as error message
            printLine();
            System.out.println("Opps! There appears to be an index error!");
            printLine();
        }
    }

    private static void addTaskToList(Task task) {
        /**
         * Adds a specified task to the inputList
         *
         * @param task which is Task object
         */
        inputList.add(task);
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + inputList.size() + " tasks in the list.");
        printLine();
    }

    private static void deleteTask(int index) {
        /**
         * Deletes a specified task from the inputList
         *
         * @param index which is the index of the task to be removed
         */

        // first, check if the provided index is valid or not
        if (index >= 0 && index < inputList.size()) {
            Task remove = inputList.remove(index);
            printLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println("   " + remove);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
            printLine();
        } else {
            // if the index is not in range, then print an error message
            printLine();
            System.out.println("Hmmm... The index provided seems to be out of range. Please try again.");
            printLine();
        }
    }

    public static void main(String[] args) throws IOException {

        // This will be starting point of the application

        //Variable to store user inputs
        String userInput;

        // Create a scanner for user input
        Scanner user_s = new Scanner(System.in);

        // Greeting the users
        printLine();
        System.out.println("Nuffle > Good day! I'm Nuffle.");
        System.out.println("Nuffle > What can I do for you today?");
        printLine();

        // Read the user input until the command "bye" is provided by the user
        while(true) {
            // Get the user input from the scanner
            userInput = user_s.nextLine();
            try {
                // Check if the user input provided is "bye", has to be lowercase
                if (userInput.equals("bye")) {
                    // Program will exit
                    printLine();
                    System.out.println("Nuffle > Bye. Hope to see you again soon!");
                    printLine();
                    break;
                } else if (userInput.equals("list")) {
                    // Program will list all the tasks that was input by the user
                    System.out.println("Here are the tasks in your list: ");
                    printLine();
                    outputList();
                    printLine();
                } else if (userInput.startsWith("mark")) {
                    // Program will mark the specified task based on the index provided

                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    markTask(index);
                } else if (userInput.startsWith("unmark")) {
                    // Program will unmark the specified task based on the index provided

                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    unMarkTask(index);
                } else if (userInput.startsWith("delete")) {
                    // Program will delete the specified task based on the index provided

                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    deleteTask(index);
                } else if (userInput.startsWith("todo")) {
                    Task newTask = Parser.parseTodoCommand(userInput);
                    if (newTask != null) {
                        addTaskToList(newTask);
                    }
                } else if (userInput.startsWith("deadline")) {
                    // Program will add a deadline task to the list
                    Task newTask = Parser.parseDeadlineCommand(userInput);
                    if (newTask != null) {
                        addTaskToList(newTask);
                    }
                } else if (userInput.startsWith("event")) {
                    // Program will add an event task to the list
                    Task newTask = Parser.parseEventCommand(userInput);
                    if (newTask != null) {
                        addTaskToList(newTask);
                    }
                } else {
                    throw NuffleException.unknown();
                }
            } catch (NuffleException e) {
                printLine();
                System.out.println("Nuffle caught an error > " + e.getMessage());
                printLine();
            }
        }
        storage.save(inputList);
        user_s.close();
    }
}
