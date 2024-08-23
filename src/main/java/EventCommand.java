import java.util.HashMap;

public class EventCommand extends Command {
    HashMap<String, String> argumentMap;

    public EventCommand(HashMap<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("argument");
        String at = argumentMap.get("from");
        String to = argumentMap.get("to");

        EventTask newEvent = new EventTask(description, at, to);
        tasks.add(newEvent);
        ui.printAddedTask(newEvent, tasks.size());

        return true;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        if (!argumentMap.containsKey("argument")) {
            throw new MissingFlagException("Missing description for event!");
        }
        if (argumentMap.get("argument").isEmpty()) {
            throw new InvalidArgumentException("The description of an event cannot be empty!");
        }

        if (!argumentMap.containsKey("from")) {
            throw new MissingFlagException("Missing time for event!");
        }
        if (argumentMap.get("from").isEmpty()) {
            throw new InvalidArgumentException("The description of an event cannot be empty!");
        }

        if (!argumentMap.containsKey("to")) {
            throw new MissingFlagException("Missing end time for event!");
        }
        if (argumentMap.get("to").isEmpty()) {
            throw new InvalidArgumentException("The description of an event cannot be empty!");
        }
    }
}
