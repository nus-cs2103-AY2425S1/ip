package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.exit();
        try (FileWriter fw = new FileWriter("data/ratchet.txt")) {
            for (Task task : tasks) {
                fw.write(task.toSave());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            ui.printWithIndent("Unable to save data!");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
