package alice.command;

import alice.storage.*;
import alice.task.*;
import alice.ui.*;
import java.io.*;

public class DeleteTask extends Command {
    public DeleteTask(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public void execute(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing task number. Usage: delete <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            ui.warn("Invalid task number. Usage: delete <task number>");
            return;
        }

        Task removedTask;
        try {
            int index = taskNumber - 1;
            removedTask = taskList.deleteTask(index);
            String[] lines = new String[]{
                "Noted. I've removed this task:",
                removedTask.toString()
            };
            ui.say(lines);
        } catch (IndexOutOfBoundsException e) {
            ui.warn("Task number out of bounds. Usage: delete <task number>");
        } catch (IOException e) {
            ui.warn("Unable to save task.");
        }
    }
}