package processes;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.*;
import tasks.Task;


/**
 * The main file of the program. Contains the main method.
 * Run the main method to start the task tracker
 */
public class MrTracker {
    public static final String FILEPATH = "./data/tasks.txt";

    public static final String DIRPATH = "./data";

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;


    /**
     * Constructor for the chatbot class. Loads data into memory and creates necessary objects for the program to run.
     *
     */
    public MrTracker() {
        this.ui = new Ui();
        this.storage = new Storage(DIRPATH, FILEPATH);
        this.parser = new Parser();
        this.taskList = new TaskList();

        storage.loadData(taskList.getTasks());
    }



    /**
     * Function that brings all processes together to make chatbot function.
     * Takes in the user's input and produces the output.
     *
     * @param input The user input.
     * @return The response by the chatbot.
     */

    public String getResponse(String input) {
        ui.showTaskList(taskList.getTasks());
        String res = null;
        PrefixString pref = parser.parseCommand(input);

        if (pref == null) {
            res = ui.showMessage("I am sorry, but I don't know what that means :-(");
            return res;
        }

        switch (pref) {
        case BYE:
            res = ui.showGoodbyeMessage();
            break;

        case LIST:
            res = ui.showTaskList(taskList.getTasks());
            break;

        case MARK:
            try {
                Task markedTask = taskList.mark(input);
                res = ui.showMarked(markedTask);
            } catch (TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                res = ui.showMessage("Error: Please provide a valid mark command!\n" +
                        "Usage: mark<space><index>");
            }
            break;

        case UNMARK:
            try {
                Task unMarkedTask = taskList.unMark(input);
                res = ui.showUnmarked(unMarkedTask);
            } catch (TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                res = ui.showMessage("Error: Please provide a valid unmark command!\n" +
                        "Usage: unmark<space><index>");
            }
            break;

        case TODO:
            try {
                Task newToDo = taskList.addToDo(input);
                res = ui.addedTask(newToDo, taskList.getSize());
            } catch (InvalidTaskNameException | EmptyTagException | SpaceInTagException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case DEADLINE:
            try {
                Task newDeadLine = taskList.addDeadline(input);
                res = ui.addedTask(newDeadLine, taskList.getSize());
            } catch (InvalidTaskNameException | InvalidDateException
                     | EmptyTagException | SpaceInTagException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case EVENT:
            try {
                Task newEvent = taskList.addEvent(input);
                res = ui.addedTask(newEvent, taskList.getSize());
            } catch (InvalidDateException | InvalidTaskNameException |
                     EmptyTagException | SpaceInTagException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case DELETE:
            try {
                Task deleted = taskList.deleteTask(input);
                res = ui.deletedTask(deleted, taskList.getSize());
            } catch (TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                res = ui.showMessage("Error: Please provide a valid delete command!\n" +
                        "Usage: delete<space><index>");
            }
            break;

        case FIND:
            ArrayList<Task> output = taskList.find(input);
            res = ui.showMatchedTasks(output, input);
            break;

        case WELCOME:
            res = ui.showWelcomeMessage("MrTracker");
            break;

        case TAG:
            try {
                Task taskToAddTags = taskList.tag(input);
                res = ui.showTaskTags(taskToAddTags);
            } catch (TaskOutOfBoundsError | EmptyTagException | SpaceInTagException e) {
                res = ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                res = ui.showMessage("Error: Please provide a valid tag command!\n" +
                        "Usage: tag<space><tags to add, separated by '#'>");
            }
            break;

        case REMOVETAGS:
            try {
                Task taskToAddTags = taskList.removeTags(input);
                res = ui.showRemoveTaskTags(taskToAddTags);
            } catch(TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                res = ui.showMessage("Error: Please provide a valid remove tag command!\n" +
                        "Usage: remove tags<space><tags to delete, separated by '#', " +
                        "or nothing here to delete all tags>");
            }
            break;

        default:
            res = ui.showMessage("I am sorry, but I don't know what that means :-(");
            break;
        }
        storage.save(taskList.getTasks());
        return res;
    }
}
