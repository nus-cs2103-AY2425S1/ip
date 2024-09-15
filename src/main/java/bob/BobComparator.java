package bob;

import java.util.Comparator;

import bob.task.Task;

/**
 * Utility class containing comparator logic for sorting tasks.
 */
public class BobComparator {

    /**
     * Comparator for sorting tasks based on their description in alphabetical order.
     */
    public static final Comparator<Task> DESCRIPTION_COMPARATOR = (t1, t2)
            -> t1.getDescription().compareToIgnoreCase(t2.getDescription());

    /**
     * Comparator for sorting tasks based on their date.
     * ToDo tasks do not have time-constraints, hence they will appear later in the list.
     */
    public static final Comparator<Task> DATE_COMPARATOR = (t1, t2)
            -> t1.getFirstDatetime().compareTo(t2.getFirstDatetime());
}
