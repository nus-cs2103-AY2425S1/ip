package duke;

import java.io.IOException;
import java.text.ParseException;

abstract class Command {

    String input;
    static boolean exit;

    abstract public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException, IOException;

    public static boolean isExit() {
        return exit;
    }
}
