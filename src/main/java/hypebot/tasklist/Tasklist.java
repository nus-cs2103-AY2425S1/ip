package hypebot.tasklist;

import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_DUPLICATE_TASK;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS;
import static hypebot.common.Messages.ERROR_NO_TASKS_HAPPENING;
import static hypebot.common.Messages.ERROR_NO_TASKS_MATCH_SEARCH;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import com.sun.jdi.request.DuplicateRequestException;

import hypebot.command.FindCommand;
import hypebot.command.HappeningCommand;
import hypebot.parser.command.FindQueryParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Task;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code Tasklist} containing a group of {@link Task}s.
 * <p>A child of {@link ArrayList}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Task
 */
public class Tasklist extends ArrayList<Task> {
    /**
     * Creates a new empty {@code Tasklist}.
     */
    public Tasklist() {
        super();
    }

    /**
     * Takes in a {@link Task} and adds it to the {@code Tasklist} and returns
     * whether the {@link Task} was added successfully.
     *
     * @param task {@link Task} to be added.
     * @return Whether the {@link Task} was added successfully
     * @throws DuplicateRequestException If the {@code Tasklist} already contains the {@link Task}
     *                                   or a {@link Task} of the same semantics.
     */
    @Override
    public boolean add(Task task) throws DuplicateRequestException {
        if (contains(task)) {
            throw new DuplicateRequestException(ERROR_DUPLICATE_TASK + (indexOf(task) + 1) + ".\n");
        }
        super.add(task);
        return true;
    }

    /**
     * Takes in an integer index (0-indexed), removes it from the {@code Tasklist}
     * and returns the {@link Task} at the previously specified index.
     *
     * @param index Index of {@link Task} to be deleted.
     * @return {@link Task} removed from {@link Tasklist}, previously at given index.
     * @throws IndexOutOfBoundsException If index provided is < 0 or > {@code size()} - 1.
     */
    @Override
    public Task remove(int index) throws IndexOutOfBoundsException {
        try {
            return super.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in an integer index (0-indexed), marks the corresponding {@link Task} as complete.
     *
     * @param index Index of {@link Task} to be marked complete.
     * @throws IndexOutOfBoundsException If index provided < 0 or > {@code size()} - 1.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        try {
            get(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in an integer index (0-indexed), marks the corresponding {@link Task} as incomplete.
     *
     * @param index Index of {@link Task} to be marked incomplete.
     * @throws IndexOutOfBoundsException If index provided < 0 or > {@code size()} - 1.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        try {
            get(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Takes in a {@link LocalDate}, returns all {@link Task}s that occur on given date.
     * <p>Makes a {@code clone()} of this instance and uses {@code stream().filter().toList()}.</p>
     *
     * @param date {@link LocalDate} created by {@link UiDateTimeParser} from input
     *             at {@link UiGuiMainWindow} for a {@link HappeningCommand}.
     * @return New {@code Tasklist} containing {@link Task}s occurring on given date.
     * @throws NoSuchElementException If {@code Tasklist} is empty or there are no {@link Task}s that
     *                                occurring on the given date.
     */
    @SuppressWarnings("unchecked")
    public Tasklist getHappeningOn(LocalDate date) throws NoSuchElementException {
        if (this.isEmpty() || this.stream().noneMatch(task -> task.isHappeningOn(date))) {
            throw new NoSuchElementException(ERROR_NO_TASKS_HAPPENING);
        }
        List<Task> tempClone = (List<Task>) clone();
        List<Task> filteredTasks= tempClone.stream().filter(task -> task.isHappeningOn(date)).toList();
        Tasklist newTasklist = new Tasklist();
        newTasklist.addAll(filteredTasks);
        return newTasklist;
    }

    /**
     * Takes in a regex {@link Pattern}, created by {@link FindQueryParser} to send to a
     * {@link FindCommand}, and a returns a new {@link Tasklist} of all {@link Task}s that
     * match the regex condition.
     * <p>Makes a {@code clone()} of this instance and uses {@code stream().filter().toList()}.</p>
     *
     * @param searchQuery {@link Pattern} search query created by {@link FindQueryParser}
     *                    from input at {@link UiGuiMainWindow} for a {@link FindCommand}.
     * @return New {@code Tasklist} containing {@link Task}s with names matching the search query.
     * @throws NoSuchElementException If {@code Tasklist} is empty or there are no {@link Task}s that
     *                                have a name with any of the keywords.
     */
    @SuppressWarnings("unchecked")
    public Tasklist getNameContains(Pattern searchQuery) throws NoSuchElementException {
        if (this.isEmpty() || this.stream().noneMatch(task -> task.nameContains(searchQuery))) {
            throw new NoSuchElementException(ERROR_NO_TASKS_MATCH_SEARCH);
        }
        List<Task> tempClone = (List<Task>) clone();
        List<Task> filteredTasks= tempClone.stream().filter(task -> task.nameContains(searchQuery)).toList();
        Tasklist newTasklist = new Tasklist();
        newTasklist.addAll(filteredTasks);
        return newTasklist;
    }

    /**
     * Returns a {@link String} representing of the {@code Tasklist} listing out
     * {@link Task}s in numerical insertion order.
     *
     * @return {@link String} representation of {@link Task}s in numerical insertion order.
     */
    @Override
    public String toString() {
        StringBuilder listMessage = new StringBuilder();
        for (Task task : this) {
            listMessage.append(indexOf(task) + 1).append(". ").append(task).append("\n");
        }
        return listMessage.toString().stripTrailing();
    }
}
