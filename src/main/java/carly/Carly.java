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

    private String executeCommand(Command command, String taskDescription) throws CarlyException {
            switch (command) {
            case BYE:
                return ui.byeMsg();
            case LIST:
                return taskPrinter.printAllTasks();
            case MARK:
                return markTask(taskDescription);
            case UNMARK:
                return unmarkTask(taskDescription);
            case DELETE:
                return deleteTask(taskDescription);
            case FIND:
                return findTask(taskDescription);
            case TODO:
                return addToDoTask(taskDescription);
            case DEADLINE:
                return addDeadlineTask(taskDescription);
            case EVENT:
                return addEventTask(taskDescription);
            case SORT:
                return this.taskList.sort();
            default:
                return "Oops, what are you trying to say again?";
            }
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
        return this.taskList.addDeadLine(taskDescription);
    }

    private String addEventTask(String taskDescription) throws CarlyException {
        return this.taskList.addEvent(taskDescription);
    }

    public static void main(String[] args) {
    }
}

