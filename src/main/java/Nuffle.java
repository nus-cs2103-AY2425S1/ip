import java.util.Scanner;
import java.util.ArrayList;

public class Nuffle {

    // This list contains all Task Class objects
    private static ArrayList<Task> inputList = new ArrayList<>();

    private static void printLine() {
        // This method will be used to print the border
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
    }

    private static void outputList() {
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
        inputList.add(task);
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + inputList.size() + " tasks in the list.");
        printLine();
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
            } else if (userInput.startsWith("todo")) {
                // Program will add a To-do task to the list

                // Get the description of the to-do task first
                String desc = userInput.substring(5);
                // add the task to the list
                Task newTask = new Todo(desc);
                addTaskToList(newTask);
            } else if (userInput.startsWith("deadline")) {
                // Program will add a deadline task to the list

                // Get the description of the deadline task as well as the day, split by the "by"
                String[] desc = userInput.substring(9).split(" /by ");
                // add the task to the list
                Task newTask = new Deadline(desc[0], desc[1]);
                addTaskToList(newTask);

            } else if (userInput.startsWith("event")) {
                // Program will add a event task to the list

                // Get the description of the event task first
                String[] desc = userInput.substring(6).split(" /from | /to ");
                // add the task to the list
                Task newTask = new Event(desc[0], desc[1], desc[2]);
                addTaskToList(newTask);
            }
            else {
                // Echo the user command
                printLine();
                System.out.println("Nuffle > added: " + userInput);
                // Create a Task object
                Task task = new Task(userInput);
                // Add the user input into the list
                inputList.add(task);
                printLine();
            }
        }
        // Once loop breaks, terminate/close the scanner
        user_s.close();
    }
}
