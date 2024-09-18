package snowy.common;

import snowy.data.SnowyException;

public class TagCommand extends Command {
    private final int index;
    private final String tag;

    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command to tag the task.
     *
     * @return a CommandResult indicating that task has been tagged.
     * @throws SnowyException if invalid index
     */
    @Override
    public CommandResult execute() throws SnowyException {
        try {
            String str = taskList.tagTask(index - 1, tag);
            return new CommandResult(str + "\nTagged task at index " + index);
        } catch (SnowyException e) {
            throw new SnowyException(e.getMessage());

        }
    }
}

