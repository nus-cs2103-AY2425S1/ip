package commands;

import java.io.IOException;

import exceptions.InvalidTaskException;
import data.TaskList;
import data.Storage;
import ui.Ui;

public class EditCommand implements Command {
    public enum Instruction {
        MARK,
        UNMARK
    }

    private String remaining;
    private Instruction instruction;

    public EditCommand(String remaining, Instruction instruction) {
        this.remaining = remaining;
        this.instruction = instruction;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(remaining);
        try {
            if (instruction == Instruction.MARK) {
                taskList.markTask(index, ui);
            } else if (instruction == Instruction.UNMARK) {
                taskList.unmarkTask(index, ui);
            }
            storage.save(taskList);
        } catch (InvalidTaskException | IOException e) {
            ui.displayString(e.getMessage());
        }

        return true;
    }
}
