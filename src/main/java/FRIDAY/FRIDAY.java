package FRIDAY;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a bot that acts as an interactive todo list to manage and track the tasks of users.
 * <p>
 * This class represents an interactive bot that tracks and manages a user's tasks, similar to a todo list.
 * </p>
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class FRIDAY {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Storage archive;
    private TaskList programArchive;

    /**
     * Constructor for bot.
     */
    public FRIDAY() {
        //divider
        this.ui = new Ui();
        String filePath = "./storage/FRIDAY.txt";
        String archiveFilePath = "./storage/archive.txt";
        this.storage = new Storage(filePath);
        this.archive = new Storage(archiveFilePath);
        try {
            this.taskList = new TaskList(storage.load());
            this.programArchive = new TaskList(archive.load());
        } catch (FRIDAYException f) {
            System.out.println("Encountered unrecognizable task type");
        }
    }

    /**
     * This method takes in a String representing user input, and returns a String representing the
     * response from the bot.
     *
     * @param userInput String representing user input.
     * @return String representing the response of the program.
     */
    public String getResponse(String userInput) {
        String response = "";
        try {
            //collect user input
            String keyword = Parser.parseCmd(userInput);
            //taskDetails is the user input without the keyword
            String taskDetails = Parser.parseTaskDetails(userInput);
            response = this.performAction(keyword, taskDetails);
            assert !Objects.equals(response, "") : "Response is null";
        } catch (FRIDAYException e) {
            return ui.printText(e.getMessage());
        }
        return response;
    }

    /**
     * This method takes in a String representing a command, and a String representing other miscellaneous information
     * entered by the user, and performs the relevant action.
     *
     * @param keyword String that represents the given command dictating the type of action that the bot should
     *                perform.
     * @param taskDetails String contains miscellaneous information about the task, if applicable.
     * @throws FRIDAYException This exception is thrown when the type of action to be performed is not recognized.
     */
    public String performAction(String keyword, String taskDetails) throws FRIDAYException {
        //keywords trigger respective actions
        switch (keyword) {
        //keywords
        case ("mark"):
            taskList.markTask(Integer.parseInt(taskDetails) - 1);
            return ui.printCheck();
        case ("unmark"):
            taskList.unMarkTask(Integer.parseInt(taskDetails) - 1);
            return ui.printUncheck();
        case ("todo"):
            Task newToDo = new ToDo(taskDetails.strip(), 0);
            assert newToDo != null : "failed to create task";
            taskList.addTask(newToDo);
            return ui.printAdd(newToDo, taskList.numTasks());
        case ("deadline"):
            Task newDeadline = Parser.parseDeadline(taskDetails);
            assert newDeadline != null : "failed to create task";
            taskList.addTask(newDeadline);
            return ui.printAdd(newDeadline, taskList.numTasks());
        case ("event"):
            Task newEvent = Parser.parseEvent(taskDetails);
            assert newEvent != null : "failed to create task";
            taskList.addTask(newEvent);
            return ui.printAdd(newEvent, taskList.numTasks());
        case ("delete"):
            Task deletedTask = taskList.getTaskAt(Integer.parseInt(taskDetails) - 1);
            taskList.removeTask(Integer.parseInt(taskDetails) - 1);
            return ui.printRemove(deletedTask, taskList.numTasks());
        case ("bye"):
            ArrayList<Task> list = this.taskList.getList();
            assert list != null : "list not found";
            storage.updateStorage(list);
            return "bye";
        case ("list"):
            return taskList.displayTasks();
        case("search"):
            String word = taskDetails;
            ArrayList<Task> searchResults = taskList.search(word);
            assert searchResults != null : "could not generate search results";
            return ui.displaySearchResults(searchResults);
        case("archive"):
            ArrayList<Task> archivedTasks = this.taskList.getList();
            assert archivedTasks != null : "list not found";
            archive.updateStorage(archivedTasks);
            this.taskList.deleteAllTasks();
            storage.updateStorage(this.taskList.getList());
            return ui.printArchive();
        case (""):
            return ui.emptyInput();
        default:
            throw new FRIDAYException("I'm sorry, I don't know what that means :<");
        }
    }
}
