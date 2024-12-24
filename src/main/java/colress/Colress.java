package colress;

import java.io.IOException;
import java.util.Objects;

import colress.exception.FileCorruptedException;
import colress.storage.ColressStorage;
import colress.storage.Storage;
import colress.tasklist.ColressTaskList;
import colress.tasklist.TaskList;

/**
 * Represents the Colress chatbot.
 */
public final class Colress {
    private final Storage storage;
    private final TaskList taskList;
    private Ui ui;
    private String commandType;
    private boolean hasError;
    private boolean isBeginnerMode;

    /**
     * Constructs Colress.
     * Colress has an Ui object which facilitates interactions with the user.
     * Colress has a Storage object which writes tasks to the text file and loads tasks from the text file
     * during start-up.
     * Colress has a TaskList object which contains an ArrayList of Tasks and perform operations on them.
     *
     * @param filePath A string representing the relative filepath for the text file containing the tasks.
     */
    public Colress(String filePath) {
        this.storage = new ColressStorage(filePath);
        this.taskList = new ColressTaskList();
        this.commandType = "greet";
        this.hasError = false;
        this.isBeginnerMode = false;
        setUi();
    }

    /**
     * Constructs Colress with the given Storage and TaskList objects.
     */
    public Colress(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.commandType = "greet";
        this.hasError = false;
        this.isBeginnerMode = false;
        setUi();
    }

    /**
     * Constructs Colress with the given parameters.
     */
    public Colress(Storage storage, TaskList taskList, boolean hasError, boolean isBeginnerMode) {
        this.storage = storage;
        this.taskList = taskList;
        this.commandType = "greet";
        this.hasError = hasError;
        this.isBeginnerMode = isBeginnerMode;
        setUi();
    }

    private void setUi() {
        this.ui = isBeginnerMode ? new ColressUiBeginner(this) : new ColressUiAdvanced(this);
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public boolean getIsBeginnerMode() {
        return isBeginnerMode;
    }

    public String greetUser() {
        return ui.welcome();
    }

    /**
     * Loads task from task file to the given TaskList object and returns
     * the contents of the TaskList object.
     */
    public String loadTasks() {
        try {
            storage.loadTasks(taskList);
            return getTasks();
        } catch (FileCorruptedException e) {
            storage.createFile();
            hasError = true;
            return e.getMessage();
        } catch (IOException e) {
            hasError = true;
            return "There is an error. Try again.";
        }
    }

    private String getTasks() {
        return ui.printTasks(taskList);
    }

    public String getResponse(String input) {
        try {
            String result = ui.processInput(input, taskList);
            if (ui.getStatus() == Status.WRITE) {
                writeToTaskFile();
            }
            return result;
        } catch (IOException e) {
            hasError = true;
            return "There is an error. Try again.";
        }
    }

    private void writeToTaskFile() throws IOException {
        storage.writeToTaskFile(taskList);
        ui.setStatus(Status.COMMAND);
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

    /**
     * Toggles between Beginner and Advanced modes for command input.
     */
    public boolean toggleMode() {
        this.isBeginnerMode = !isBeginnerMode;
        setUi();
        return isBeginnerMode;
    }

    public static void main(String[] args) {
        System.out.println("Running Colress...");
    }
}
