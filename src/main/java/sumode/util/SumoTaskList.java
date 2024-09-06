package sumode.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sumode.exception.AlreadyMarkedException;
import sumode.exception.AlreadyUnmarkedException;
import sumode.exception.LatestSaveException;
import sumode.exception.NonExistentTaskException;
import sumode.exception.SumoDException;
import sumode.exception.UnknownCommandException;
import sumode.exception.WrongSyntaxForCommandException;
import sumode.task.Task;
import sumode.ui.Ui;

/**
 * Stores all tasks.
 */
public class SumoTaskList {

    private final List<Task> tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for SumoTaskList
     *
     * @param storage Used to store data into local drive.
     * @param ui UI for all output.
     */
    public SumoTaskList(Storage storage, Ui ui) throws IOException {
        //initialising
        this.tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
        loadDataFromStorage();
    }

    private void loadDataFromStorage() throws FileNotFoundException {
        if (this.storage == null) {
            return;
        }
        String[] data = storage.load();
        for (int i = 0; i < data.length; i++) {
            try {
                this.tasks.add(Task.createFromData(data[i]));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                this.ui.warnCorruptedSaveFile(i + 1);
            }
        }
    }


    /**
     * Returns a boolean that indicates whether to terminate the programme after this command is finish.
     *
     * @param command Command given by user (first word in the input).
     * @param item Everything after the first word.
     * @return a boolean that indicates whether to terminate the programme after this command is finish.
     * @throws NonExistentTaskException due to wrong index given to MARK/UNMARK/DELETE command
     * @throws AlreadyUnmarkedException due to user trying to unmark an already unmarked task
     * @throws AlreadyMarkedException due to user trying to mark an already marked task
     */
    public boolean execute(Command command, String item)
            throws SumoDException {
        switch(command) {
        case BYE:
        case EXIT: // added this to allow flexibility though not required by qn
            return true;
        case LIST:
            this.ui.printTask(this.tasks, false);
            break;
        case FIND:
            List<Task> filtered = getFilteredTasks(item);
            this.ui.printTask(filtered, true);
            break;
        case MARK: {
            int index = getIndex(command, item);
            tasks.get(index - 1).mark();
            ui.printTaskMarked(tasks.get(index - 1));
            tryToSave();
            break;
        }
        case UNMARK: {
            int index = getIndex(command, item);
            tasks.get(index - 1).unmark();
            ui.printTaskUnmarked(tasks.get(index - 1));
            tryToSave();
            break;
        }
        case DELETE: {
            int index = getIndex(command, item);
            ui.printTaskRemoved(tasks.get(index - 1), tasks.size() - 1);
            tasks.remove(index - 1);
            tryToSave();
            break;
        }
        case TODO:
        case DEADLINE:
        case EVENT:
            Task newlyAdded = Task.of(command, item);
            tasks.add(newlyAdded); // used factory method to be more neat and OOP
            ui.printTaskAdded(newlyAdded, tasks.size());
            tryToSave();
            break;
        default:
            throw new UnknownCommandException(command);
        }
        return false;
    }

    private List<Task> getFilteredTasks(String item) {
        return this.tasks.stream()
                .filter(task -> task.toString().contains(item))
                .toList();
    }

    private void tryToSave() throws LatestSaveException {
        if (storage != null) {
            storage.save(this.tasks);
        }
    }

    private int getIndex(Command command, String item) throws WrongSyntaxForCommandException, NonExistentTaskException {
        int index;
        try {
            index = Integer.parseInt(item);
        } catch (IllegalArgumentException e) {
            throw new WrongSyntaxForCommandException(command);
        }

        if (index > tasks.size() || index <= 0) {
            throw new NonExistentTaskException(index);
        }
        return index;
    }
}
