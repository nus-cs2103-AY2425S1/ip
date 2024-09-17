package echoa;

import java.io.IOException;

public abstract class Command {
    Ui ui;
    Parser parser;
    TaskList taskList;
    Storage storage;

    public Command(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.parser = parser;
        this.taskList = taskList;
        this.storage = storage;
    }

    public abstract void execute(String line) throws ListOutOfBoundsException, InvalidIndexInputException, InvalidTaskContentException, UpdateFormatException, IOException, DateFormatException, TimeFormatException;

    public abstract String getMessage();
}
