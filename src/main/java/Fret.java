import command.Command;

import psu.Storage;
import psu.Ui;

import task.TaskList;

public class Fret {
    Storage storage;
    Ui fretUi;
    TaskList userTasks;

    public Fret(String filepath) {
        this.storage = new Storage(filepath);
        this.fretUi = new Ui();
        this.userTasks = new TaskList(storage.loadTasksFromMemory());
    }

    /**
     * Runs the Fret chatbot program
     */
    public void run() {
        fretUi.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            Command userCommand = fretUi.processUserInput();
            isExit = userCommand.isExitCommand();
            String result = userCommand.execute(userTasks);
            fretUi.printBotOutputString(result);
        }

        fretUi.closeUi();
        storage.writeTasksToMemory(userTasks.taskListToFile());
    }

    public static void main(String[] args) {
        new Fret("data/taskList.txt").run();
    }
}
