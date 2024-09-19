package ekud.commands;

import java.util.Comparator;
import java.util.Locale;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.task.comparators.SortTaskByCompletion;
import ekud.task.comparators.SortTaskByDeadline;
import ekud.task.comparators.SortTaskByDescription;
import ekud.task.comparators.SortTaskByPriority;
import ekud.ui.Ui;

/**
 * {@link Command} that sorts a {@link TaskList} by a given field.
 *
 * @author uniqly
 */
public class SortCommand extends Command {
    public static final String BY_TOKEN = "/by";
    public static final String ORDER_TOKEN = "/order";
    private static final String SORT_BY_DEADLINE = "deadline";
    private static final String SORT_BY_COMPLETE = "completion";
    private static final String SORT_BY_DESCRIPTION = "description";
    private static final String SORT_BY_PRIORITY = "priority";
    private static final String ASC = "ascending";
    private static final String DSC = "descending";

    private final Comparator<Task> comparator;
    private boolean isAscending = true; // true by default

    /**
     * Creates a {@link SortCommand}.
     *
     * @param by The field to sort the task list by.
     * @param order The order in which to sort the list by.
     * @throws EkudException If there are invalid inputs.
     */
    public SortCommand(String by, String order) throws EkudException {
        String emptyByResponse = "Mr forgetful has forgotten to include a field to sort the list by! OOPS!";

        if (by == null || by.isEmpty()) {
            throw new EkudException(emptyByResponse);
        }
        comparator = determineComparator(by);

        if (order == null || order.isEmpty()) {
            return;
        }
        isAscending = determineAscending(order);
    }

    private static Comparator<Task> determineComparator(String by) throws EkudException {
        String invalidFieldResponse = String.format("""
                        Look buddy, I know you I can do it all, but I can't.
                        Please instead try sorting by the available fields
                          '%s', '%s', '%s', or '%s'""",
                SORT_BY_COMPLETE, SORT_BY_DEADLINE, SORT_BY_DESCRIPTION, SORT_BY_PRIORITY);

        return switch (by.toLowerCase()) {
            // CHECKSTYLE.OFF: Indentation
            case SORT_BY_COMPLETE -> new SortTaskByCompletion();
            case SORT_BY_DEADLINE -> new SortTaskByDeadline();
            case SORT_BY_DESCRIPTION -> new SortTaskByDescription();
            case SORT_BY_PRIORITY -> new SortTaskByPriority();
            default -> throw new EkudException(invalidFieldResponse);
            // CHECKSTYLE.ON: Indentation
        };
    }

    private static boolean determineAscending(String order) throws EkudException {
        String invalidOrderResponse = String.format("""
                        I know it may be annoying but I'm only looking for '%s' or '%s'.""",
                ASC, DSC);

        return switch (order.toLowerCase(Locale.ENGLISH)) {
            // CHECKSTYLE.OFF: Indentation
            case ASC -> true;
            case DSC -> false;
            default -> throw new EkudException(invalidOrderResponse);
            // CHECKSTYLE.ON: Indentation
        };
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        String responseFormat = "Abracadabra! Your list is now sorted:";

        // sort list
        tasks.sortWith(comparator, isAscending);
        ui.addFormattedToBuffer(responseFormat);

        // print list
        tasks.outputContentsToUi(ui);

        // save sorted list
        storage.overwriteTasks(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
