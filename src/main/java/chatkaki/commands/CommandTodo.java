package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.tasks.TaskList;
import chatkaki.tasks.Todo;

public class CommandTodo extends Command {
    private String[] inputs;

    public CommandTodo(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public String execute() {
        if (inputs.length <= 1) {
            return "The description of a todo cannot be empty.";
        }
        return TaskList.addTask(new Todo(false, inputs[1]), false);
    }
}
