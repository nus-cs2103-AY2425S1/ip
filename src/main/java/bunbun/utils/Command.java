package bunbun.utils;

import bunbun.exceptions.BunbunException;

import java.util.ArrayList;

/**
 * This class implements Commands that can be executed by Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */

public class Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * Instantiates a Command object.
     *
     * @param taskList TaskList to execute the commands on.
     * @param ui UI to print out result of executed commands.
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Calls different methods based on the command provided,
     * checking for validity of the command.
     *
     * @param tokens ArrayList<String> of commands input by user to be executed.
     */
    public String execute(ArrayList<String> tokens) {
        try {
            String firstWord = tokens.get(0);
            if (firstWord.equals("list")) {
                return this.taskList.displayList();
            } else if (firstWord.equals("mark")) {
                return this.taskList.markDoneTask(tokens);
            } else if (firstWord.equals("todo")) {
                return this.taskList.addToDo(tokens);
            } else if (firstWord.equals("timebox")) {
                return this.taskList.addTimeBox(tokens);
            } else if (firstWord.equals("deadline")) {
                return this.taskList.addDeadline(tokens);
            } else if (firstWord.equals("event")) {
                return this.taskList.addEvent(tokens);
            } else if (firstWord.equals("delete")) {
                return this.taskList.deleteTask(tokens);
            } else if (firstWord.equals("find")) {
                return this.taskList.searchAndDisplay(tokens);
            } else {
                return this.ui.response("Sorry, I don't understand ><");
            }
        } catch (BunbunException e) {
            return this.ui.response(e.getMessage());
        }
    }
}
