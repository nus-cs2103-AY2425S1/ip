package carly;

import java.io.IOException;

import carly.exception.CarlyException;
import carly.ui.Ui;
import carly.utils.Parser;
import carly.utils.Storage;
import carly.utils.TaskList;
import carly.utils.TaskPrinter;
import carly.utils.Command;

/**
 *  Represents a chatbot named Carly that manages a list of tasks.
 *  Can interact with the chatbot using Commands.
 */
public class Carly {

    /** Manages the list of tasks for the chatbot. */
    private final TaskList taskList;
    private final Ui ui;
    private final TaskPrinter taskPrinter;
    private final Storage listStorage;

    public Carly() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.taskPrinter = new TaskPrinter(this.taskList);
        this.listStorage = new Storage("./data/CarlyList.txt");

        ui.welcomeMsg();
    }

    /**
     * Handles the user input and returns the response from the chatbot.
     *
     * @param input the user input.
     * @return the chatbot's response.
     */
    public String getResponse(String input) {
        String response;
        try {
            Parser parser = new Parser(input);
            Command command = parser.getCommand();
            String taskDescription = parser.getDetailsAfterCommand(command);

            response = executeCommand(command, taskDescription);
            saveTasks();
        } catch (IOException | CarlyException e) {
            response = e.getMessage();
        }
        assert !response.isEmpty(): "Response should not be empty.";
        return response;
    }

    /**
     * Executes the given command based on the user's input and returns the corresponding response.
     *
     * @param command the command to be executed, as parsed from the user's input.
     * @param taskDescription the description of the task associated with the command (if applicable).
     * @return the response generated after executing the command.
     * @throws CarlyException if there is an issue executing the command, such as an invalid task description or task not found.
     */
    private String executeCommand(Command command, String taskDescription) throws CarlyException {
        return switch (command) {
            case BYE -> ui.byeMsg();
            case LIST -> taskPrinter.printAllTasks();
            case MARK -> markTask(taskDescription);
            case UNMARK -> unmarkTask(taskDescription);
            case DELETE -> deleteTask(taskDescription);
            case FIND -> findTask(taskDescription);
            case TODO -> addToDoTask(taskDescription);
            case DEADLINE -> addDeadlineTask(taskDescription);
            case EVENT -> addEventTask(taskDescription);
            case SORT -> this.taskList.sort();
        };
    }

    /** Saves the current task list to a file. */
    private void saveTasks() throws IOException, CarlyException {
        listStorage.savesFile(this.taskList);
    }

    /** Task-related helper methods **/

    private String markTask(String taskDescription) throws CarlyException {
        return this.taskList.mark(taskDescription);
    }

    private String unmarkTask(String taskDescription) throws CarlyException {
        return this.taskList.unmark(taskDescription);
    }

    private String deleteTask(String taskDescription) throws CarlyException {
        return this.taskList.delete(taskDescription);
    }

    private String findTask(String taskDescription) throws CarlyException {
        return this.taskList.find(taskDescription);
    }

    private String addToDoTask(String taskDescription) throws CarlyException {
        return this.taskList.addToDo(taskDescription);
    }

    private String addDeadlineTask(String taskDescription) throws CarlyException {
        return this.taskList.addDeadline(taskDescription);
    }

    private String addEventTask(String taskDescription) throws CarlyException {
        return this.taskList.addEvent(taskDescription);
    }

    public static void main(String[] args) {
    }
}

