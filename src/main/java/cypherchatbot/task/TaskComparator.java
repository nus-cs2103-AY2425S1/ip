package cypherchatbot.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        if (task1.TASK_PRIORITY == 1) {
            assert task1 instanceof ToDo : "Task priority is not set properly";
            ToDo currentTask = (ToDo) task1;
            if (!currentTask.TASK_PRIORITY.equals(task2.TASK_PRIORITY)) {
                    return currentTask.TASK_PRIORITY > task2.TASK_PRIORITY ? 1 : -1;
                } else {
                    assert task2 instanceof ToDo : "Task priority is not set properly";
                    return 0;
                }
            } else if (task1.TASK_PRIORITY == 2) {
                assert task1 instanceof Deadline: "Task priority is not set properly";
                Deadline currentTask = (Deadline) task1;
                if (!currentTask.TASK_PRIORITY.equals(task2.TASK_PRIORITY)) {
                    return currentTask.TASK_PRIORITY > task2.TASK_PRIORITY ? 1 : -1;
                } else {
                    assert task2 instanceof Deadline : "Task priority is not set properly";
                    Deadline compareTask = (Deadline) task2;
                    return currentTask.compareTo(compareTask);
                }
            } else  {
                assert task1 instanceof Event: "Task priority is not set properly";
                Event currentTask = (Event) task1;
                if (!currentTask.TASK_PRIORITY.equals(task2.TASK_PRIORITY)) {
                    return currentTask.TASK_PRIORITY > task2.TASK_PRIORITY ? 1 : -1;
                } else {
                    assert task2 instanceof Event : "Task priority is not set properly";
                    Event compareTask = (Event) task2;
                    return currentTask.compareTo(compareTask);
                }
            }
        }
}
