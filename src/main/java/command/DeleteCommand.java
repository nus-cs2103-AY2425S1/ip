package command;

import java.util.Arrays;

import exception.InvalidArgumentException;
import exception.LevelHundredException;
import exception.MissingArgumentException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The DeleteCommand class deletes an existing task from the task list and storage file
 */
public class DeleteCommand extends UserCommand {
    /**
     * Deletes task at user specified index from storage and task list
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        String[] words = userInput.split(" ");

        if (words.length == 1) {
            LevelHundredException e = new MissingArgumentException("delete", "task index");
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            LevelHundredException e = new InvalidArgumentException("delete", invalidString);
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        assert (words.length == 2) : "Number of words in delete command should be 2";

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = taskList.removeTask(idx);
            storage.update(taskList.getTaskList());

            StringBuilder res = new StringBuilder("Noted. I've removed this task:\n");
            res.append(t + "\n");
            res.append("Now your have " + taskList.size() + " tasks in the list.");
            this.setResponse(res.toString());

            ui.printDeleteTask(t, taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LevelHundredException exception = new InvalidArgumentException("task index", words[1]);
            this.setResponse(exception.toString());
            ui.printException(exception);
        }
    }
}