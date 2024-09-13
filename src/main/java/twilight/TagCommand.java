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
    public TagCommand(String details) {
        String[] split = details.split(" ", 2);
        this.taskNum = Integer.valueOf(split[0]) - 1;
        this.tag = split[1];
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String output = "The task has been tagged: " + tasks.tag(taskNum, tag);
        try {
            storage.saveData(tasks.getTasks());
            return output;
        } catch (IOException e) {
            return "error saving";
        }
    }
}

