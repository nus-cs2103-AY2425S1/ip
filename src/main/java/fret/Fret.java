package fret;

import javafx.util.Pair;

import command.Command;

import psu.Storage;
import psu.Ui;

import task.TaskList;

public class Fret {
    private Storage storage;
    private Ui fretUi;
    private TaskList userTasks;

    public Fret(String filepath) {
        this.storage = new Storage(filepath);
        this.fretUi = new Ui();
        this.userTasks = new TaskList(storage.loadTasksFromMemory());
    }

    public String welcomeUser() {
        return fretUi.welcomeUser();
    }

    /**
     * Runs the Fret chatbot program.
     */
    public void run() {
        fretUi.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            // // Command userCommand = fretUi.processUserInput();
            // isExit = userCommand.isExitCommand();
            // String result = userCommand.execute(userTasks);
            // fretUi.printBotOutputString(result);
        }

        fretUi.closeUi();
        storage.writeTasksToMemory(userTasks.taskListToFile());
    }

    /**
     * Gets the response from Fret based on user input.
     * 
     * @param input input from the user.
     * @return Fret's reply to the user.
     */
    public Pair<String, Boolean> getResponse(String input) {
        Command userCommand = fretUi.processUserInput(input);
        boolean isExit = userCommand.isExitCommand();

        if (isExit) {
            storage.writeTasksToMemory(userTasks.taskListToFile());
        }
        
        String result = userCommand.execute(userTasks);
        return new Pair<String, Boolean>(result, isExit);
        // fretUi.printBotOutputString(result);
    }

    // public static void main(String[] args) {
    //     new Fret("data/taskList.txt").run();
    // }
}
