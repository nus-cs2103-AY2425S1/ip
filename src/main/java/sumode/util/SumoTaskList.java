package sumode.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sumode.exception.AlreadyMarkedException;
import sumode.exception.AlreadyUnmarkedException;
import sumode.exception.MissingDetailsException;
import sumode.exception.NonExistentTaskException;
import sumode.exception.UnknownCommandException;
import sumode.exception.WrongSyntaxForCommandException;
import sumode.task.Task;

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

        //adding tasks based on data
        String[] datas = storage.load();
        for (int i = 0; i < datas.length; i++) {
            try {
                tasks.add(Task.createFromData(datas[i]));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                ui.corruptedSaveFile(i + 1);
            }
        }


    }

    /**
     * Constructor for SumoTaskList.
     * <p>
     * Should only be used when storage not available.
     *
     * @param ui UI for all output.
     */
    public SumoTaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.storage = null;
        this.ui = ui;
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
            throws NonExistentTaskException, UnknownCommandException,
            WrongSyntaxForCommandException, AlreadyMarkedException,
            AlreadyUnmarkedException, MissingDetailsException {
        switch(command) {
        case BYE:
        case EXIT: // added this to allow flexibility though not required by qn
            return true;
        case LIST:
            this.ui.printTask(this.tasks, false);
            break;
        case FIND:
            List<Task> filtered = this.tasks.stream()
                    .filter(task -> task.toString().contains(item))
                    .toList();
            this.ui.printTask(filtered, true);
            break;
        case MARK: {
            int index;
            try {
                index = Integer.parseInt(item);
            } catch (IllegalArgumentException e) {
                throw new WrongSyntaxForCommandException(command);
            }

            if (index > tasks.size() || index <= 0) {
                throw new NonExistentTaskException(index);
            }
            tasks.get(index - 1).mark();
            ui.mark(tasks.get(index - 1));

        }
            if (storage != null) {
                storage.save(this.tasks);
            }
            break;
        case UNMARK: {
            int index;
            try {
                index = Integer.parseInt(item);
            } catch (IllegalArgumentException e) {
                throw new WrongSyntaxForCommandException(command);
            }

            if (index > tasks.size() || index <= 0) {
                throw new NonExistentTaskException(index);
            }
            tasks.get(index - 1).unmark();
            ui.unmark(tasks.get(index - 1));
        }
            if (storage != null) {
                storage.save(this.tasks);
            }

            break;
        case DELETE: {
            int index;
            try {
                index = Integer.parseInt(item);
            } catch (IllegalArgumentException e) {
                throw new WrongSyntaxForCommandException(command);
            }
            if (index > tasks.size() || index <= 0) {
                throw new NonExistentTaskException(index);
            }
            ui.removeTask(tasks.get(index - 1), tasks.size() - 1);
            tasks.remove(index - 1);
        }
            if (storage != null) {
                storage.save(this.tasks);
            }
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            Task newlyAdded = Task.of(command, item);
            tasks.add(newlyAdded); // used factory method to be more neat and OOP
            ui.addTask(newlyAdded, tasks.size());
            if (storage != null) {
                storage.save(this.tasks);
            }
            break;
        default:
            throw new UnknownCommandException(command);
        }
        return false;
    }
}
