package commands;

import static util.Utility.INDENT;
import static util.Utility.NEW_LINE;

import java.util.Arrays;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;


/**
 * Concrete implementation of delete command class.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        String[] indexes = details[1].split("\\s+");
        int[] toBeRemovedIndexes = new int[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            int idx = Integer.parseInt(indexes[i]);
            if (!tl.isValidIdx(idx)) {
                ui.setResponse(
                        String.format("Someones tryna be funny, idx: %d is out of range!", idx));
                ui.printResponse();
                return;
            }
            toBeRemovedIndexes[i] = idx;
        }

        StringBuilder tasksRemoved = new StringBuilder();
        int offset = 0;
        toBeRemovedIndexes = Arrays.stream(toBeRemovedIndexes).sorted().distinct().toArray();
        Arrays.sort(toBeRemovedIndexes);
        for (int i : toBeRemovedIndexes) {
            Task t = tl.deleteTask(i + offset, storage);
            tasksRemoved.append(INDENT + t.toString() + NEW_LINE);
            offset--;
        }

        ui.setResponse("Ok! I've removed this task:", tasksRemoved.toString(),
                String.format("You now have %d tasks in your list.", tl.size()));
        ui.printResponse();
    }
}
