package command;

import java.util.Arrays;

import exception.InvalidArgumentException;
import exception.LevelHundredException;
import exception.MissingArgumentException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends UserCommand {
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
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