package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

import java.util.Collections;
import java.util.List;

/**
 * The MarkTaskCommand contains all the sub-commands that should be executed when a Mark/Unmark Task Command
 * is given by the user.
 */
public class MarkTaskCommand extends Command {

    private final boolean isComplete;
    private final List<Integer> tasksToMark;


    /**
     * Creates a new MarkTaskCommand object. The input list of integers assumes the list is 1-indexed, the consructor
     * will decrement each integer by one to fit the 0-based index of the actual tasklist.
     *
     * @param markIndicesList A list of integers representing the index of the tasks to be marked.
     * @param isComplete A boolean value representing whether the task should be marked as complete or incomplete
     */
    public MarkTaskCommand(List<Integer> markIndicesList, boolean isComplete) {
        this.isComplete = isComplete;
        this.tasksToMark = markIndicesList;
        Collections.sort(tasksToMark);

        tasksToMark.replaceAll(x -> x - 1);
    }

    private String markTaskHelper(int taskToMark, Tasklist tasklist, Storage storage) throws TayooException {
        if (tasklist.markTask(taskToMark)) {
            storage.updateTxt(taskToMark, isComplete);
            return "Marked: " + tasklist.getTaskStr(taskToMark) + "\n";
        } else {
            return "Already marked: " + tasklist.getTaskStr(taskToMark) + "\n";
        }
    }
    private String unmarkTaskHelper(int taskToMark, Tasklist tasklist, Storage storage) throws TayooException {
        if (tasklist.unmarkTask(taskToMark)) {
            storage.updateTxt(taskToMark, isComplete);
            return "Unmarked: " + tasklist.getTaskStr(taskToMark) + "\n";
        } else {
            return "Already unmarked: " + tasklist.getTaskStr(taskToMark) + "\n";
        }
    }

    private String markTaskCommandHelper(Tasklist tasklist, Storage storage) throws TayooException{
        StringBuilder toReturn = new StringBuilder();

        if (isComplete) {
            toReturn.append("Okay! I've marked these tasks:\n");
        } else {
            toReturn.append("Okay! I've unmarked these tasks:\n");
        }


        for (int taskToMark : tasksToMark) {
            if (taskToMark < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToMark >= Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number <= " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToMark >= tasklist.getSize()) {
                toReturn.append("Could not find task number ").append(taskToMark + 1).append("\n");
                continue;
            }

            if (isComplete) {
                toReturn.append(markTaskHelper(taskToMark, tasklist, storage));
            } else {
                toReturn.append(unmarkTaskHelper(taskToMark, tasklist, storage));
            }
        }

        return toReturn.toString();
    }

    /**
     * Executes the actual marking/unmarking of the task when called. The method iterates through each of the integers
     * in the tasksToMark list and marks each one in incrementing order.
     *
     * @param tasklist tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with marking the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(markTaskCommandHelper(tasklist, storage));
    }

    /**
     * Executes the MarkTaskCommand and returns the string response instead of directly printing the result to the
     * terminal. Functions exactly the same as execute() otherwise.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this MarkTaskCommand
     * @throws TayooException if there is any error with marking the command (i.e. invalid input)
     */
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return markTaskCommandHelper(tasklist, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
