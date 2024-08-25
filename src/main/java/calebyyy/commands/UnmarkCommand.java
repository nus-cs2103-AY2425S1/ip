package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.TaskList;
import calebyyy.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    @Override
    public void execute(String input) throws InvalidArgumentException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }

        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
        taskList.unmarkTask(taskNumber);
    }
}