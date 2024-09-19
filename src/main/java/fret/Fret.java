package fret;

import ps.Parser;
import ps.Storage;

import command.Command;

import task.TaskList;

public class Fret {
    private Storage storage;
    private TaskList userTasks;

    public Fret(String filepath) {
        this.storage = new Storage(filepath);
        this.userTasks = new TaskList(storage.loadTasksFromMemory());
    }

    /**
     * Welcomes the user with ASCII art and a greeting.
     * 
     * @return the greeting.
     */
    public String welcomeUser() {
        String logo = "F R E T \n";
        
        String welcomeString = "Initiating...\n" + logo 
                + "\nPersonal AI Fret, coming online!\nHey, how can I be of assistance?";
        
        return welcomeString;
    }

    /**
     * Gets the response from Fret based on user input.
     * 
     * @param input input from the user.
     * @return Fret's reply to the user.
     */
    public String getResponse(String input) {
        Command userCommand = Parser.parse(input);
        boolean isExit = userCommand.isExitCommand();

        if (isExit) {
            storage.writeTasksToMemory(userTasks.taskListToFile());
        }
        
        return userCommand.execute(userTasks);
    }
}
