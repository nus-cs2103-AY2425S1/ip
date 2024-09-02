package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.PotongException;

import java.io.IOException;

public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String command) {
        super(command);
        this.keyword = command;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws PotongException, IOException {
        return tasks.find(this.keyword);
    }
}
