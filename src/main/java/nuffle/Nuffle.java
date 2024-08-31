package nuffle;

import nuffle.exception.NuffleException;
import nuffle.parser.Parser;
import nuffle.task.Task;
import nuffle.ui.Ui;
import nuffle.storage.Storage;
import nuffle.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Nuffle {
    private static Storage storage = new Storage("./data/Nuffle.txt");

    private static TaskList inputList;

    static {
        try {
            inputList = new TaskList(storage.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will print out all the task that is stored in the inputList
     */
    private static void outputList() {
        // If there is no user input in the list
        if (inputList.getInputList().isEmpty()) {
            System.out.println("List is empty. No input added.");
        } else {
            // Iterate over the list to print out all the user inputs
            for (int index = 0; index < inputList.getSize(); index++) {
                System.out.println("" + (index + 1) + ". " + inputList.getTask(index));
            }
        }
    }

    /**
     * Marks a specified task based on the given index
     *
     * @param index the index of the task to mark
     */
    private static void markTask(int index) {

        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.getSize()) {
            Task currTask = inputList.getTask(index);
            currTask.markAsDone();
            Ui.markTaskMessage(currTask);
        } else {
            // print as error message
            Ui.markTaskError();
        }
    }


    /**
     * Unmarks a specified task based on the given index
     *
     * @param index the index of the task to unmark
     */
    private static void unMarkTask(int index) {

        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.getSize()) {
            Task currTask = inputList.getTask(index);
            currTask.markNotDone();
            Ui.unmarkTaskMessage(currTask);
        } else {
            // print as error message
            Ui.unmarkTaskError();
        }
    }

    /**
     * Adds a specified task to the inputList
     *
     * @param task which is Task object
     */
    private static void addTaskToList(Task task) {
        inputList.addTask(task);
        Ui.addTaskMessage(task, inputList.getSize());
    }

    /**
     * Deletes a specified task from the inputList
     *
     * @param index which is the index of the task to be removed
     */
    private static void deleteTask(int index) {


        // first, check if the provided index is valid or not
        if (index >= 0 && index < inputList.getSize()) {
            Task remove = inputList.deleteTask(index);
            Ui.deleteTaskMessage(remove, inputList.getSize());
        } else {
            // if the index is not in range, then print an error message
            Ui.deleteTaskError();
        }
    }

    public static void main(String[] args) throws IOException {

        // This will be starting point of the application
        Ui.welcomeMessage();

        //Variable to store user inputs
        String userInput;

        // Create a scanner for user input
        Scanner user_s = new Scanner(System.in);

        // Read the user input until the command "bye" is provided by the user
        while (true) {
            // Get the user input from the scanner
            userInput = user_s.nextLine();
            try {
                // Check if the user input provided is "bye", has to be lowercase
                if (userInput.equals("bye")) {
                    // Program will exit
                    Ui.byeMessage();
                    break;
                } else if (userInput.equals("list")) {
                    // Program will list all the tasks that was input by the user
                    System.out.println("Here are the tasks in your list: ");
                    outputList();
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
                Ui.exceptionErrorMessage(e);
            }
        }
        storage.save(inputList.getInputList());
        user_s.close();
    }
}
