package commands;

import java.io.IOException;

import exceptions.InvalidTaskException;
import data.TaskList;
import data.Storage;
import ui.Ui;

/**
 * Represents the command to edit a Task
 */
public class EditCommand implements Command {
    /**
     * Different instructions given to program to edit a Task
     */
    public enum Instruction {
        MARK,
        UNMARK
    }

    private String remaining;
    private Instruction instruction;

    /**
     * Constructor for EditCommand
     *
     * @param remaining input to tell program which task to edit
     * @param instruction tells program how to edit the task
     */
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
