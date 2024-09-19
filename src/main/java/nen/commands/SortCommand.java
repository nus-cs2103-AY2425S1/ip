package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a command which sort the given type of task
 * @author Gan Ren Yick (A0276246X)
 */
public class SortCommand extends Command {
    private String taskType;
    private String className;
    /**
     * Instantiates SortCommand
     * @param name of the command
     * @param arg is string of the name of task to sort
     */
    public SortCommand(String name, String arg) {
        this.name = name;
        className = arg;
        taskType = arg;
        if (!arg.equals("todo") && !arg.equals("event") && !arg.equals("deadline")) {
            taskType = null;
        }
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskType == null) {
            description += "Sort what?? todo/event/deadline";
            return;
        }
        description += "Nice! I've sorted \"" + className + "\" for you :)\n"
                + taskList.sort(taskType).toString() + "\n";
    }
}
