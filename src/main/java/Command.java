import java.util.HashMap;

abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    abstract boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException;

    void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        for (String arg : CommandManager.CommandType.fromString(this.name).commandArgs) {
            if (!argumentMap.containsKey(arg)) {
                throw new MissingFlagException("Missing" + arg + " for " + name + "!");
            }
            if (argumentMap.get(arg).isEmpty()) {
                throw new InvalidArgumentException("The " + arg + " of a " + name + " cannot be empty!");
            }
        }
    }
}
