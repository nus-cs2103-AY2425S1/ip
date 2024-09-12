package task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDos && t2 instanceof ToDos) {
            return t1.toString()
                    .substring(7)
                    .compareTo(t2.toString().substring(7));
        }

        if (t1 instanceof Deadlines && t2 instanceof Deadlines) {
            return ((Deadlines) t1)
                    .getDeadline()
                    .compareTo(((Deadlines) t2).getDeadline());
        }

        if (t1 instanceof Events && t2 instanceof Events) {
            return ((Events) t1)
                    .getStart()
                    .compareTo(((Events) t2).getStart());
        }

        // Todos should always appear first
        if (t1 instanceof ToDos) {
            return -1;
        }

        return t1 instanceof Deadlines
                ? t2 instanceof Events
                ? -1
                : 1
                : 1;
    }
}
