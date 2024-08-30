package alex.command;

import java.util.ArrayList;
import java.util.Scanner;

import alex.Storage;
import alex.TaskList;
import alex.Ui;

public class FindCommand extends Command {
    private Scanner lineScanner;
    public FindCommand(Scanner lineScanner) {
        this.lineScanner = lineScanner;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> arrOfStr = new ArrayList<>();
        while (this.lineScanner.hasNext()) {
            arrOfStr.add(this.lineScanner.next());
        }

        tasks.findWord(String.join(" ", arrOfStr), ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
