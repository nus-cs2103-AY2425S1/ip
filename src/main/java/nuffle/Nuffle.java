package nuffle;

import nuffle.exception.NuffleException;
import nuffle.parser.Parser;
import nuffle.task.Task;
import nuffle.ui.Ui;
import nuffle.storage.Storage;
import nuffle.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Nuffle {
    private Storage storage;

    private TaskList inputList;

    private Ui ui;

    public Nuffle(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.inputList = new TaskList(this.storage.load());
            this.ui = new Ui();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will print out all the task that is stored in the inputList
     */
    private String outputList() {
        StringBuilder output = new StringBuilder();

        // If there is no user input in the list
        if (inputList.getInputList().isEmpty()) {
            output.append("List is empty. No input added.");
        } else {
            // Iterate over the list to construct the output string with all user inputs
            for (int index = 0; index < inputList.getSize(); index++) {
                output.append(index + 1).append(". ").append(inputList.getTask(index)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Marks a specified task based on the given index
     *
     * @param index the index of the task to mark
     */
    private String markTask(int index) {
        String response;
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.getSize()) {
            Task currTask = inputList.getTask(index);
            currTask.markAsDone();
            response = ui.markTaskMessage(currTask);
        } else {
            // print as error message
            response = ui.markTaskError();
        }
        return response;
    }


    /**
     * Unmarks a specified task based on the given index
     *
     * @param index the index of the task to unmark
     */
    private String unMarkTask(int index) {
        String response;
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < inputList.getSize()) {
            Task currTask = inputList.getTask(index);
            currTask.markNotDone();
            response = ui.unmarkTaskMessage(currTask);
        } else {
            // print as error message
            response = ui.unmarkTaskError();
        }
        return response;
    }

    /**
     * Adds a specified task to the inputList
     *
     * @param task which is Task object
     */
    private String addTaskToList(Task task) {
        inputList.addTask(task);
        return ui.addTaskMessage(task, inputList.getSize());
    }

    /**
     * Deletes a specified task from the inputList
     *
     * @param index which is the index of the task to be removed
     */
    private String deleteTask(int index) {
        // first, check if the provided index is valid or not
        if (index >= 0 && index < inputList.getSize()) {
            Task remove = inputList.deleteTask(index);
            return ui.deleteTaskMessage(remove, inputList.getSize());
        } else {
            // if the index is not in range, then print an error message
            return ui.deleteTaskError();
        }
    }

    public String greetUser() {
        return ui.printWelcomeMessage();
    }

    public String getResponse(String input) throws IOException {
        return responseHandler(input);
    }

    public String responseHandler(String userInput) throws IOException {
        String response = null;

        try {
            // Check if the user input provided is "bye", has to be lowercase
            if (userInput.equals("bye")) {
                // Program will exit
                response = ui.byeMessage();
            } else if (userInput.equals("list")) {
                // Program will list all the tasks that was input by the user
                System.out.println("Here are the tasks in your list: ");
                response = outputList();
            } else if (userInput.startsWith("mark")) {
                // Program will mark the specified task based on the index provided

                int index = Integer.parseInt(userInput.substring(5)) - 1;
                response = markTask(index);
            } else if (userInput.startsWith("unmark")) {
                // Program will unmark the specified task based on the index provided

                int index = Integer.parseInt(userInput.substring(7)) - 1;
                response = unMarkTask(index);
            } else if (userInput.startsWith("delete")) {
                // Program will delete the specified task based on the index provided

                int index = Integer.parseInt(userInput.substring(7)) - 1;
                response = deleteTask(index);
            } else if (userInput.startsWith("todo")) {
                Task newTask = Parser.parseTodoCommand(userInput);
                if (newTask != null) {
                    response = addTaskToList(newTask);
                }
            } else if (userInput.startsWith("deadline")) {
                // Program will add a deadline task to the list
                Task newTask = Parser.parseDeadlineCommand(userInput);
                if (newTask != null) {
                    response = addTaskToList(newTask);
                }
            } else if (userInput.startsWith("event")) {
                // Program will add an event task to the list
                Task newTask = Parser.parseEventCommand(userInput);
                if (newTask != null) {
                    response = addTaskToList(newTask);
                }
            } else if (userInput.startsWith("find")) {
                // get the description and parse as argument to the find function
                ArrayList<Task> foundTasks = Parser.parseFindCommand(userInput, inputList);
                response = ui.displayFoundTasks(foundTasks);
            } else {
                throw NuffleException.unknown();
            }
        } catch (NuffleException e) {
            System.out.println("hi");
            response = ui.exceptionErrorMessage(e);
        }

        storage.save(inputList.getInputList());
        return response;
    }
    public static void main(String[] args) {
        new Nuffle("./data/Nuffle.txt");
    }
}