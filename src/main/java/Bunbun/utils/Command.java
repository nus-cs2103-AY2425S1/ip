package Bunbun.utils;

import Bunbun.exceptions.BunbunException;

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
    public void execute(ArrayList<String> tokens) {
        try {
            String firstWord = tokens.get(0);
            int len = tokens.size();
            if (firstWord.equals("list")) {
                this.taskList.displayList();
            } else if (firstWord.equals("mark")) {
                if (len == 2) {
                    String val = tokens.get(1);
                    this.taskList.markDoneTask(Integer.parseInt(val));
                } else {
                    this.ui.response("Specify 1! positive integer to mark task as complete D:");
                }
            } else if (firstWord.equals("todo")) {
                this.taskList.addToDo(tokens);
            } else if (firstWord.equals("deadline")) {
                this.taskList.addDeadline(tokens);
            } else if (firstWord.equals("event")) {
                this.taskList.addEvent(tokens);
            } else if (firstWord.equals("delete")) {
                if (len == 2) {
                    this.taskList.deleteTask(Integer.parseInt(tokens.get(1)));
                } else {
                    this.ui.response("Specify 1! positive integer to delete task D:");
                }
            } else {
                this.ui.response("Sorry, I don't understand ><");
            }
        } catch (BunbunException e) {
            e.printStackTrace();
        }
    }
}
