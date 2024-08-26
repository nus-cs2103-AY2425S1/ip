import java.util.ArrayList;

public class DeleteCommand extends Command {
    TaskManager taskManager;
    FileManager fileManager;
    String userInput;
    ArrayList<Task> tasks;
    public DeleteCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.fileManager = fileManager;
        this.tasks = taskManager.getTasksArray();
    }
    @Override
    public void runCommand() throws TaskManagerException {

        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
                Task taskToDelete = this.tasks.remove(taskNumber);
                System.out.println("Got it! 🗑️ I've waved goodbye to this task:");
                System.out.println(taskToDelete.toString());
                System.out.println("Your list just got lighter! 🌟 " +
                        "Now you're down to " + this.tasks.size() + " tasks. Keep up the momentum!");
            } else {
                throw new TaskManagerException("\uD83D\uDEAB Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                        TaskManagerException.ErrorType.TASK_OUT_OF_RANGE);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. " +
                    "Please enter a task number after mark/unmark/delete command. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)",
                    TaskManagerException.ErrorType.INVALID_MARK_TASK_NUMBER);
        } finally {
            this.fileManager.writeTasksToFile(this.tasks);
        }
    }

}
