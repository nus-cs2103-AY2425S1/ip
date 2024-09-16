package chatbot.command;

import chatbot.logic.TaskList;

public class FindCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    private String query;

    public FindCommand(TaskList taskList, String query) {
        this.taskList = taskList;
        this.query = query;
    }

    @Override
    public String run() {
        return taskList.find(query);
    }
}
