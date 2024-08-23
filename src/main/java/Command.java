import java.util.HashMap;

abstract class Command {

    abstract boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException;

    abstract void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException;
}
