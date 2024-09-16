package chatbot.command;

import chatbot.logic.TaskList;
import chatbot.task.Task;
import chatbot.task.Todo;

public class TodoCommand extends Command {
    private TaskList taskList;
    private String name;

    public TodoCommand(TaskList taskList, String name) {
        this.taskList = taskList;
        this.name = name;
    }
    @Override
    public String run() {
        Task newTask = new Todo(this.name);
        return this.taskList.add(newTask);
    }
}
