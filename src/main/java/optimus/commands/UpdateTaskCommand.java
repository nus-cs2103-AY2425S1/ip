package optimus.commands;

import java.util.Objects;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.InvalidCommandException;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.Task;

/**
 * Updates a given task
 */
public class UpdateTaskCommand extends Command {

    private final int taskNum;
    private final String desc;
    private final String firstDate;
    private final String secondDate;

    /**
     * @param taskNum - task index
     * @param desc - new description, "" if no change is made
     * @param firstDate - new date (either /by for deadline or /from for events), "" if no change is made
     * @param secondDate - new date (end date for events), "" if no change is made
     */
    public UpdateTaskCommand(int taskNum, String desc, String firstDate, String secondDate) throws InvalidCommandException {
        this.taskNum = taskNum;
        if (Objects.equals(desc, "") && Objects.equals(firstDate, "") && Objects.equals(secondDate, "")) {
            throw new InvalidCommandException("Nothing has been changed");
        }
        this.desc = desc;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) throws OptimusExceptions {
        Task task = tasks.getTask(taskNum);
        String taskType = task.getTaskType();
        switch(taskType) {
        case "T" -> {
            if (!Objects.equals(this.firstDate, "") || !Objects.equals(this.secondDate, "")) {
                throw new InvalidCommandException("There are no dates to update");
            }
            if (!Objects.equals(this.desc, "")) {
                task.updateDescription(this.desc);
            }
            break;
        } case "D" -> {
            if (!Objects.equals(this.secondDate, "")) {
                throw new InvalidCommandException("There is no end date to update");
            }
            if (!Objects.equals(this.desc, "")) {
                task.updateDescription(this.desc);
            }
            if (!Objects.equals(this.firstDate, "")) {
                task.updateFirstDate(this.firstDate);
            }
            break;
        } case "E" -> {
            if (!Objects.equals(this.desc, "")) {
                task.updateDescription(this.desc);
            }
            if (!Objects.equals(this.firstDate, "")) {
                task.updateFirstDate(this.firstDate);
            }
            if (!Objects.equals(this.secondDate, "")) {
                task.updateSecondDate(this.secondDate);
            }
        } default -> {
        }
        }
        storage.rewriteEntireFile(tasks.getList());
        return "I have updated the task. It now reads as:\n" + task;
    }
}
