package twilight;

import java.io.IOException;

public class TagCommand extends Command {
    protected int taskNum;
    protected String tag;

    /**
     * Instantiates a  command.
     *
     * @param details The number of the task to be tagged.
     */
    public TagCommand(String details) throws InvalidInputException {
        String[] split = details.split(" ", 2);
        try {
            this.taskNum = Integer.valueOf(split[0]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("tag command must have a valid integer task number.");
        }
        try {
            this.tag = split[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Tag commands must include the tag ex. #fun");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String output = "The task has been tagged:\n" + tasks.tag(taskNum, tag);
        try {
            storage.saveData(tasks.getTasks());
            return output;
        } catch (IOException e) {
            return "error saving";
        }
    }
}

