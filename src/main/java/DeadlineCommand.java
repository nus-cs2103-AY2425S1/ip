import java.util.HashMap;

public class DeadlineCommand extends Command {
    HashMap<String, String> argumentMap;

    public DeadlineCommand(HashMap<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("argument");
        String by = argumentMap.get("by");

        DeadlineTask newDeadline = new DeadlineTask(description, by);
        tasks.add(newDeadline);
        ui.printAddedTask(newDeadline, tasks.size());

        return true;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        if (!argumentMap.containsKey("argument")) {
            throw new MissingFlagException("Missing description for deadline!");
        }
        if (argumentMap.get("argument").isEmpty()) {
            throw new InvalidArgumentException("The description of a deadline cannot be empty!");
        }

        if (!argumentMap.containsKey("by")) {
            throw new MissingFlagException("The deadline of a deadline cannot be empty!");
        }
        if (argumentMap.get("by").isEmpty()) {
            throw new InvalidArgumentException("The deadline of a deadline cannot be empty!");
        }
    }
}
