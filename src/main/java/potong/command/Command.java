package potong.command;

import potong.exceptions.PotongException;
import potong.Storage;
import potong.TaskList;
import potong.Ui;

import java.io.IOException;

public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws PotongException, IOException;

}
