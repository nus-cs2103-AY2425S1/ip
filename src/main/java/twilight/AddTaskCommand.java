package twilight;

import java.io.IOException;

/**
 * Represents a Command to add a particular instance of an event.
 */
public class AddTaskCommand extends Command {
    protected int type;
    protected String details;

    /**
     * Instantiates an AddTaskCommand.
     *
     * @param type The type of task to be added.
     * @param details Description and if applicable timing of task.
     */
    public AddTaskCommand(int type, String details) {
        this.type = type;
        this.details = details;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String addition = "";
        if (type == 3) {
            addition = tasks.add(new Todo(details));
        } else if (type == 4) {
            String[] split = details.split(" /from | /to ");
            try {
                addition = tasks.add(new Event(split[0], split[1], split[2]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException("Events must have the format:\nevent EventName /from startTime /to endTime");
            }
        } else {
            String[] split = details.split(" /by ");
            try {
                addition = tasks.add(new Deadline(split[0], split[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException("Deadlines must have the format:\ndeadline deadlineName /by YYYY-MM-DD");
            }
        }
        try {
            storage.saveData(tasks.getTasks());
            return addition;
        } catch (IOException e) {
            return "error saving data after your last input";
        }
    }
}
