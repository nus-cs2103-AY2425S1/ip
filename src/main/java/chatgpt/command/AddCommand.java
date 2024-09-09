package chatgpt.command;

import chatgpt.exception.ChatBotException;

import chatgpt.task.TaskList;
import chatgpt.task.Task;
import chatgpt.task.ToDos;
import chatgpt.task.Deadlines;
import chatgpt.task.Events;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public class AddCommand extends Command {

    private enum Type {
        TODO,
        EVENT,
        DEADLINE
    }

    private String input;
    private Type taskType;

    public AddCommand(String taskType, String input){
        this.taskType = Type.valueOf(taskType);
        this.input = input;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task newTask = null;
        String task;
        switch (taskType) {
        case TODO:
            newTask = new ToDos(input);
            break;

        case DEADLINE:
            task = input.split("/by")[0];
            String deadline = input.split("/by")[1];
            newTask = new Deadlines(task, deadline);
            break;

        case EVENT:
            task = input.split("/from")[0];
            String date = input.split("/from")[1];
            String startDate = date.split("/to")[0];
            String endDate = date.split("/to")[1];
            newTask = new Events(task, startDate, endDate);
            break;
        }

        tasks.add(newTask);
        ui.showAddTask(newTask, tasks.size());
        storage.save(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
