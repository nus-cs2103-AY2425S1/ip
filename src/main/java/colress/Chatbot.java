package colress;

import java.util.Objects;

import colress.storage.ColressStorage;
import colress.storage.Storage;
import colress.tasklist.ColressTaskList;
import colress.tasklist.TaskList;

/**
 * An abstract class for a chatbot.
 */
public abstract class Chatbot {
    private final Storage storage;
    private final TaskList taskList;
    private Ui ui;
    private String commandType;
    private boolean hasError;
    private boolean isBeginnerMode;

    /**
     * Constructs a chatbot.
     * Chatbots have an Ui object which facilitates interactions with the user.
     * Chatbots have a Storage object which writes tasks to the text file and loads tasks from the text file
     * during start-up.
     * Chatbots have a TaskList object which contains an ArrayList of Tasks and perform operations on them.
     *
     * @param filePath A string representing the relative filepath for the text file containing the tasks.
     */
    public Chatbot(String filePath) {
        this.storage = new ColressStorage(filePath);
        this.taskList = new ColressTaskList();
        this.commandType = "greet";
        this.hasError = false;
        this.isBeginnerMode = false;
    }

    /**
     * Constructs a chatbot with the given Storage and TaskList objects.
     */
    public Chatbot(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.commandType = "greet";
        this.hasError = false;
        this.isBeginnerMode = false;
    }

    /**
     * Constructs Colress with the given parameters.
     */
    public Chatbot(Storage storage, TaskList taskList, boolean hasError, boolean isBeginnerMode) {
        this.storage = storage;
        this.taskList = taskList;
        this.commandType = "greet";
        this.hasError = hasError;
        this.isBeginnerMode = isBeginnerMode;
    }

    public Storage getStorage() {
        return this.storage;
    }
    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public String getCommandType() {
        if (hasError) {
            hasError = false;
            return "error";
        }
        return commandType;
    }

    public void setCommandType(String commandType) {
        if (Objects.equals(commandType, "error")) {
            hasError = true;
        } else {
            this.commandType = commandType;
        }
    }

    public boolean isBeginnerMode() {
        return isBeginnerMode;
    }

    public boolean hasError() {
        return isBeginnerMode;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String greetUser() {
        return ui.welcome();
    }

    /**
     * Toggles between Beginner and Advanced modes for command input.
     */
    public boolean toggleMode() {
        this.isBeginnerMode = !isBeginnerMode;
        return isBeginnerMode;
    }

    public abstract String loadTasks();
    public abstract String getResponse(String input);
}
