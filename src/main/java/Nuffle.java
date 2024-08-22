import java.util.Scanner;
import java.util.ArrayList;

public class Nuffle {

    // This list contains all Task Class objects
    private static ArrayList<Task> inputList = new ArrayList<>();

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

    public static void main(String[] args) {

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

                    // Get the index from the user input (e.g. mark 2)
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    // Mark the task as done by calling the function
                    markTask(index);
                } else if (userInput.startsWith("unmark")) {
                    // Program will unmark the specified task based on the index provided

                    // Get the index from the user input (e.g. unmark 2)
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    // Unmark the task by calling the function
                    unMarkTask(index);
                } else if (userInput.startsWith("delete")) {
                    // Program will delete the specified task based on the index provided

                    // Get the index from teh user input (e.g. delete 3)
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    // Delete the task by calling the function
                    deleteTask(index);
                } else if (userInput.startsWith("todo")) {
                    // Program will add a To-do task to the list

                    // Get the description of the to-do task first
                    String desc = userInput.substring(4);
                    // ensure that the description is not empty. If it is, throw an exception
                    if (desc.trim().isEmpty()) {
                        throw NuffleException.noDesc("todo");
                    }
                    // add the task to the list
                    Task newTask = new Todo(desc);
                    addTaskToList(newTask);
                } else if (userInput.startsWith("deadline")) {
                    // Program will add a deadline task to the list

                    // ensure that the command contains the /by
                    if (!userInput.contains("/by")) {
                        throw NuffleException.checkDeadlineFormat();
                    }
                    // Get the description of the deadline task as well as the day, split by the "by"
                    String[] desc = userInput.substring(8).split(" /by ");
                    // ensure that the description is not empty. If it is, throw an exception
                    if (userInput.substring(8).trim().isEmpty()) {
                        throw NuffleException.noDesc("deadline");
                    }
                    // ensure that after /by has an input
                    if (desc.length < 2 || desc[1].trim().isEmpty()) {
                        throw NuffleException.checkDeadlineParams();
                    }
                    // add the task to the list
                    Task newTask = new Deadline(desc[0], desc[1]);
                    addTaskToList(newTask);
                } else if (userInput.startsWith("event")) {
                    // Program will add an event task to the list

                    // ensure that the command contains but the /to and /from
                    if (!(userInput.contains("/from") || userInput.contains("/to"))) {
                        throw NuffleException.checkEventFormat();
                    }

                    // Get the description of the event task first
                    String[] desc = userInput.substring(5).split(" /from | /to ");
                    // ensure that the description is not empty. If it is, throw an exception
                    if (userInput.substring(5).trim().isEmpty()) {
                        throw NuffleException.noDesc("event");
                    }
                    // ensure that to / from has input
                    if (desc.length < 3 || desc[1].trim().isEmpty() || desc[2].trim().isEmpty()) {
                        throw NuffleException.checkEventParams();
                    }
                    // add the task to the list
                    Task newTask = new Event(desc[0], desc[1], desc[2]);
                    addTaskToList(newTask);
                } else {
                    throw NuffleException.unknown();
                }
            } catch (NuffleException e) {
                printLine();
                System.out.println("Nuffle caught an error > " + e.getMessage());
                printLine();
            }
        }
        // Once loop breaks, terminate/close the scanner
        user_s.close();
    }
}
