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
 * The UpdateStatusCommand updates the status of a task in the task list and storage file
 */
public class UpdateStatusCommand extends UserCommand {
    /**
     * Updates status of the task at user specified index from storage and task list
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        String[] words = userInput.split(" ");
        String command = words[0];

        if (words.length == 1) {
            LevelHundredException e = new MissingArgumentException(command, "task index");
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            LevelHundredException e = new InvalidArgumentException(command, invalidString);
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        assert (words.length == 2) : "Number of words in update status command should be 2";

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = taskList.getTaskList().get(idx);
            if (command.equals("mark")) {
                t.mark();
                this.setResponse("Nice! I've marked this task as done:\n" + t);
                ui.printSuccessfulMark(t);
            } else {
                t.unmark();
                this.setResponse("OK, I've marked this task as not done yet:\n" + t);
                ui.printSuccessfulUnmark(t);
            }
            storage.update(taskList.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LevelHundredException exception = new InvalidArgumentException("task index", words[1]);
            this.setResponse(exception.toString());
            ui.printException(exception);
        }
    }
}