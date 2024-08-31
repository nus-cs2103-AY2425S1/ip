package colress;

import java.io.IOException;

import colress.exception.FileCorruptedException;

/**
 * Represents the Colress chatbot.
 */
public final class Colress {
    private final Ui UI;
    private final Storage STORAGE;
    private final TaskList TASK_LIST;

    /**
     * Constructor for the Colress class.
     * Colress has an Ui object which facilitates interactions with the user.
     * Colress has a Storage object which writes tasks to the text file and loads tasks from the text file
     * during start-up.
     * Colress has a TaskList object which contains an ArrayList of Tasks and perform operations on them.
     *
     * @param filePath A string representing the relative filepath for the text file containing the tasks.
     */
    public Colress(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        TASK_LIST = new TaskList();
    }

    /**
     * Runs the Colress chatbot.
     * The method first calls the Ui object to print a welcome message for the user.
     * The method then calls the Storage object to load tasks from the text file.
     * If the Storage object is unable to read a non-empty file and load the tasks, Colress will prompt the user to
     * delete the corrupted file.
     * The method then calls the Ui object to print the list of tasks for the user.
     * The method contains a loop that checks whether an exit command has been called, and if so, exits the loop and
     * the program.
     * In the loop, Ui object processes the input, then the Storage object writes any changes to the list of tasks
     * to the text file.
     */
    public void run() {
        try {
            UI.welcome();
            UI.printLoadTaskStatus(STORAGE.loadTasks(TASK_LIST));
            UI.printTasks(TASK_LIST, true);
            while (!UI.getHasCalledExitCommand()) {
                UI.processInput(TASK_LIST);
                STORAGE.writeToTaskFile(TASK_LIST);
            }
        } catch (FileCorruptedException e) {
            UI.print(String.valueOf(e));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Colress("task.txt").run();
    }
}
