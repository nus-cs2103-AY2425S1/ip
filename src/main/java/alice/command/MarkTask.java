package alice.command;

import alice.storage.*;
import alice.task.*;
import alice.ui.*;
import java.io.*;

public class MarkTask extends Command {
    public MarkTask(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public void execute(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing task number. Usage: mark <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            ui.warn("Invalid task number. Usage: mark <task number>");
            return;
        }

        try {
            int index = taskNumber - 1;
            Task markedTask = taskList.markTask(index);
            String[] lines = new String[]{
                "Nice! I've marked this task as done:",
                markedTask.toString()
            };
            ui.say(lines);
        } catch (IndexOutOfBoundsException e) {
            ui.warn("Task number out of bounds. Usage: mark <task number>");
        } catch (IOException e) {
            ui.warn("Unable to save task.");
        }
    }
}