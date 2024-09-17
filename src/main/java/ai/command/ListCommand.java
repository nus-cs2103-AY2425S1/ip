package ai.command;

import ai.TaskList;
import ai.Ui;

/**
 * Executes the command that lists out all tasks in the TaskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        String taskList = tasks.list();
        if (taskList == "") {
            return "Too bad... your task list is void like your personality :p\n"
                    + "Ohnoes, was that a lil too mean? Just messin with ya...\n";
        }
        return "Here are all of your tasks...\n\n"
                + taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
