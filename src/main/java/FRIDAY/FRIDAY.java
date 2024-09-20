package FRIDAY;

import java.util.ArrayList;
import java.util.Objects;

/**
 * this bot acts as an interactive todo list to manage and track the tasks of users
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class FRIDAY {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Storage archive;
    private TaskList programArchive;

    /**
     * constructor for bot
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
     * starts the bot
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
     * causes the bot to perform the action corresponding to the user input.
     * storage is updated after every performed action
     *
     * @param keyword indicates the type of action that the bot should perform
     * @param taskDetails contains miscellaneous information about the task, if applicable
     * @throws FRIDAYException exception is thrown when the type of action to be performed is not recognized
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
            throw new FRIDAYException("It appears that you have attempted to log an unrecognized class type. Please try again");
        }
    }
}
