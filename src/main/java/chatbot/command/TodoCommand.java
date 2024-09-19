package chatbot.command;

import chatbot.logic.TaskList;
import chatbot.task.Task;
import chatbot.task.Todo;

/**
 * Represents the "todo" command, which creates a todo task and adds it to the tasklist
 */
public class TodoCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** Name of the task */
    private String name;

    /**
     * Constructs the TodoCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param name The name of the task
     */
    public TodoCommand(TaskList taskList, String name) {
        this.taskList = taskList;
        this.name = name;
    }

    /**
     * Executes the Todo command, adds a Todo task to the tasklist
     *
     * @return The String result of the command after it is run
     */
    @Override
    public String run() {
        Task newTask = new Todo(this.name);
        return this.taskList.add(newTask);
    }
}
