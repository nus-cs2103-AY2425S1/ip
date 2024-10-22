package chatbot.impl.utils;

import chatbot.impl.task.Deadline;
import chatbot.impl.task.Event;
import chatbot.impl.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class TaskSorter {

    public static ArrayList<Task> cloneAndSortByDate(ArrayList<Task> tasks) {
        // Clone the list
        ArrayList<Task> clonedList = new ArrayList<>(tasks);

        // Sort by date
        clonedList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                LocalDate date1 = extractDate(t1);
                LocalDate date2 = extractDate(t2);

                if (date1 == null && date2 == null) {
                    return 0; // Both have no date, so they are equal
                } else if (date1 == null) {
                    return 1; // t1 has no date, so it should come after t2
                } else if (date2 == null) {
                    return -1; // t2 has no date, so t1 should come before t2
                } else {
                    return date1.compareTo(date2); // Compare actual dates
                }
            }
        });

        return clonedList;
    }

    // Helper method to extract the relevant date from the task
    private static LocalDate extractDate(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return ((Event) task).getFrom();
        }
        // ToDo or any other task type has no date, return null
        return null;
    }
}
