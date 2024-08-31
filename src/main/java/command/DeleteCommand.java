package command;

import java.util.Arrays;

import exception.*;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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
            ui.printException(new MissingArgumentException("delete", "task index"));
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            ui.printException(new InvalidArgumentException("delete", invalidString));
            return;
        }

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = taskList.removeTask(idx);
            storage.update(taskList.getTaskList());
            ui.printDeleteTask(t, taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printException(new InvalidArgumentException("task index", words[1]));
        }
    }
}