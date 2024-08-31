package command;

import java.util.Arrays;

import exception.*;
import task.*;
import ui.Ui;

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
            ui.printException(new MissingArgumentException(command, "task index"));
            return;
        }
        if (words.length > 2) {
            String invalidString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            ui.printException(new InvalidArgumentException(command, invalidString));
            return;
        }

        try {
            int idx = Integer.parseInt(words[1]) - 1;
            Task t = taskList.getTaskList().get(idx);
            if (command.equals("mark")) {
                t.mark();
                ui.printSuccessfulMark(t);
            } else {
                t.unmark();
                ui.printSuccessfulUnmark(t);
            }
            storage.update(taskList.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printException(new InvalidArgumentException("task index", words[1]));
        }
    }
}