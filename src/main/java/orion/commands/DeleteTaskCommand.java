package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Task;

public class DeleteTaskCommand extends Command {

    int taskNo;

    public DeleteTaskCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws OrionException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to delete task %d.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.deleteTask(taskNo - 1);
        ui.printDeleteTask(tasks, task);
    }
}
