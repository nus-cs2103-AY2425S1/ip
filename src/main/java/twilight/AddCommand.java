package twilight;

import java.io.IOException;

/**
 * Represents a Command to add a particular instance of an event.
 */
public class AddCommand extends Command {
    protected int type;
    protected String details;

    public AddCommand(int type, String details) {
        this.type = type;
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String addition = "";
        if (type == 3) {
            addition = tasks.add(new Todo(details));
        } else if (type == 4) {
            String[] split = details.split(" /from | /to ");
            addition = tasks.add(new Event(split[0], split[1], split[2]));
        } else {
            String[] split = details.split(" /by ");
            addition = tasks.add(new Deadline(split[0], split[1]));
        }
        System.out.println(addition);
        try {
            storage.saveData(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("error saving");
        }
    }
}
