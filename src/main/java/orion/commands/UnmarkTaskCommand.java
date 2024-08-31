package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import orion.tasks.Task;

import orion.exceptions.OrionInputException;

public class UnmarkTaskCommand extends Command {

    int taskNo;

    public UnmarkTaskCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws OrionInputException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as uncompleted.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.unmarkTask(taskNo - 1);
        ui.printUnmarkTask(task);
    }
}