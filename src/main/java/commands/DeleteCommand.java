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
        System.out.println("Len: " + indexes.length);
        int[] toBeRemovedIndexes = new int[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(indexes[i]);
            System.out.println();
            System.out.println();
            System.out.println();
            int idx = Integer.parseInt(indexes[i]);
            if (!tl.isValidIdx(idx)) {
                ui.setResponse(
                        String.format("Someones tryna be funny, idx: %d is out of range!", idx));
                return;
            }
            toBeRemovedIndexes[i] = idx;
        }

        StringBuilder tasksRemoved = new StringBuilder();
        int offset = 0;
        Arrays.sort(toBeRemovedIndexes);
        for (int i = 0; i < toBeRemovedIndexes.length; i++) {
            System.out.println("Index to delete is: " + toBeRemovedIndexes[i]);
            Task t = tl.deleteTask(toBeRemovedIndexes[i] + offset, storage);
            tasksRemoved.append(INDENT + t.toString() + NEW_LINE);
            offset--;
        }

        ui.setResponse("Ok! I've removed this task:", tasksRemoved.toString(),
                String.format("You now have %d tasks in your list.", tl.size()));
        ui.printResponse();
        // try {
        // int idx = Integer.parseInt(details[1]);
        // if (!tl.isValidIdx(idx)) {
        // ui.setResponse(
        // String.format("Someones tryna be funny, idx: %d is out of range!", idx));
        // return;
        // }
        // Task t = tl.deleteTask(idx, storage);
        // ui.setResponse("Ok! I've removed this task:", INDENT + t.toString(),
        // String.format("You now have %d tasks in your list.", tl.size()));
        // } catch (NumberFormatException e) {
        // ui.setResponse("Invalid index: " + details[1] + " is not a number!");
        // } finally {
        // ui.printResponse();
        // }
    }
}
