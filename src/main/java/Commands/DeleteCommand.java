package Commands;

import Task.TaskList;
import Task.Task;

public class DeleteCommand extends Command{
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }
    @Override
    public void execute(TaskList taskList) {
        try {
            Task removedTask = taskList.removeTask(indexToDelete);
            System.out.println("----------------\n" +
                    "WOOHOO! The following task has been ELIMINATED:\n " +
                    removedTask + "\n" +
                    "HUH you still have " + taskList.getSize() + " tasks remaining??\n" +
                    "----------------\n");
            taskList.writeToStorage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No valid index was given!!");
        }
    }
}
