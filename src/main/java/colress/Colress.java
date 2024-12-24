package colress;

import java.io.IOException;

import colress.exception.FileCorruptedException;
import colress.storage.Storage;
import colress.tasklist.TaskList;

/**
 * Represents the Colress chatbot.
 */
public final class Colress extends Chatbot {

    /**
     * Constructs Colress chatbot.
     *
     * @param filePath A string representing the relative filepath for the text file containing the tasks.
     */
    public Colress(String filePath) {
        super(filePath);
        setUi();
    }

    /**
     * Constructs Colress chatbot with the given Storage and TaskList objects.
     */
    public Colress(Storage storage, TaskList taskList) {
        super(storage, taskList);
        setUi();
    }

    /**
     * Constructs Colress with the given parameters.
     */
    public Colress(Storage storage, TaskList taskList, boolean hasError, boolean isBeginnerMode) {
        super(storage, taskList, hasError, isBeginnerMode);
        setUi();
    }

    private void setUi() {
        setUi(isBeginnerMode() ? new ColressUiBeginner(this) : new ColressUiAdvanced(this));
    }

    /**
     * Loads task from task file to the given TaskList object and returns
     * the contents of the TaskList object.
     */
    @Override
    public String loadTasks() {
        try {
            getStorage().loadTasks(getTaskList());
            return getTasks();
        } catch (FileCorruptedException e) {
            getStorage().createFile();
            setHasError(true);
            return e.getMessage();
        } catch (IOException e) {
            setHasError(true);
            return "There is an error. Try again.";
        }
    }

    private String getTasks() {
        return getUi().printTasks(getTaskList());
    }

    @Override
    public String getResponse(String input) {
        try {
            String result = getUi().processInput(input, getTaskList());
            if (getUi().getStatus() == Status.WRITE) {
                writeToTaskFile();
            }
            return result;
        } catch (IOException e) {
            setHasError(true);
            return "There is an error. Try again.";
        }
    }

    private void writeToTaskFile() throws IOException {
        getStorage().writeToTaskFile(getTaskList());
        getUi().setStatus(Status.COMMAND);
    }

    @Override
    public boolean toggleMode() {
        boolean result = super.toggleMode();
        setUi();
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Running Colress...");
    }
}
