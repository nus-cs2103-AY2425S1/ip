package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TaskList;
import llama.exceptions.InvalidTaskException;
import llama.ui.Ui;

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
     * Creates a command to edit a task
     *
     * @param remaining input to tell program which task to edit
     * @param instruction tells program how to edit the task
     */
    public EditCommand(String remaining, Instruction instruction) {
        this.remaining = remaining;
        this.instruction = instruction;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String response = "";
        int index = Integer.parseInt(remaining);
        try {
            if (instruction == Instruction.MARK) {
                response = taskList.markTask(index, ui);
            } else if (instruction == Instruction.UNMARK) {
                response = taskList.unmarkTask(index, ui);
            }
            storage.save(taskList);
        } catch (InvalidTaskException | IOException e) {
            response = ui.displayString(e.getMessage());
        }

        return response;
    }
}
