package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;
import knight2103.tasks.TaskType;

import java.io.IOException;
import java.util.Comparator;

/**
 * Models after a command that sorts a full list of task.
 */
public class SortCommand extends Command {
    SortCommand() {
        super(CommandVerb.SORT);
    }

    /**
     * Executes the SortCommand which lists out all the tasks stored in the list of tasks, in a sorted manner.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The sorted list of tasks stored after command execution in the bot's GUI.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Comparator<Task> comparator = generateComparator();
        try {
            tasks.sort(comparator);
            storage.saveToFile(tasks);
            String tasksInString = tasks.toString();
            return ui.showSorted(tasksInString);
        } catch (IOException e) { // from saveToFile() in Storage class
            return "Failed to execute Command:\nProblems creating an instance of FileWriter";
        }
    }

    /**
     * Generates a comparator instance via anonymous Inner class, specifying the order at which tasks
     * should follow. For this comparator, tasks not marked as done are shown first, tasks marked as done
     * are shown at the bottom. Within the marked or unmarked task category, the tasks are shown in the
     * order of TodoTask, DeadlineTask then EventTask. Within the TodoTask, tasks are sorted alphabetically
     * in terms of their task description. Within DeadlineTask, tasks are sorted based on deadlines in
     * chronological order. Within EventTask, tasks are sorted based on Start date&time in chronological
     * order. For both deadline and event tasks, within their own category, if the deadline dates and start
     * date&time are the same between the different tasks respectively, these tasks will be sorted based on
     * alphabetical order in terms of their task description.
     * @return Comparator instance.
     */
    private Comparator<Task> generateComparator() {
        return new Comparator<Task>() { // Anonymous Inner Class
            private final static int DONE_PRIORITY = 0;
            private final static int NOT_DONE_PRIORITY = 1;

            private final static int TODO_PRIORITY = 3;
            private final static int DEADLINE_PRIORITY = 2;
            private final static int EVENT_PRIORITY = 1;

            @Override
            public int compare(Task task1, Task task2) {
                final int SAME_ORDER = 0;

                int isDoneOrder = checkIsDoneOrder(task1, task2);
                if (isDoneOrder != SAME_ORDER) {
                    return isDoneOrder;
                }

                int taskTypeOrder = checkTaskTypeOrder(task1, task2);
                if (taskTypeOrder != SAME_ORDER) {
                    return taskTypeOrder;
                }

                int timingOrder = SAME_ORDER;
                if (task1 instanceof DeadlineTask deadlineTask1 && task2 instanceof DeadlineTask deadlineTask2) {
                    timingOrder = checkDeadlineOrder(deadlineTask1, deadlineTask2);
                } else if (task1 instanceof EventTask eventTask1 && task2 instanceof EventTask eventTask2) {
                    timingOrder = checkEventTimeOrder(eventTask1, eventTask2);
                }
                if (timingOrder != SAME_ORDER) {
                    return timingOrder;
                }

                return checkDescriptionOrder(task1, task2);
            }

            private int checkIsDoneOrder(Task task1, Task task2) {
                return rankIsDone(task2) - rankIsDone(task1);
            }

            private int rankIsDone(Task task) {
                if (task.isDone()) {
                    return DONE_PRIORITY;
                } else {
                    assert !task.isDone();
                    return NOT_DONE_PRIORITY;
                }
            }

            private int checkTaskTypeOrder(Task task1, Task task2) {
                int task1TaskTypeRank = rankTaskType(task1.showTaskType());
                int task2TaskTypeRank = rankTaskType(task2.showTaskType());
                return task2TaskTypeRank - task1TaskTypeRank;
            }

            private int rankTaskType(TaskType taskType) {
                if (taskType == TaskType.TODO) {
                    return TODO_PRIORITY;
                } else if (taskType == TaskType.DEADLINE) {
                    return DEADLINE_PRIORITY;
                } else {
                    assert taskType == TaskType.EVENT;
                    return EVENT_PRIORITY;
                }
            }

            private int checkDeadlineOrder(DeadlineTask task1, DeadlineTask task2) {
                return task1.getDeadline().compareTo(task2.getDeadline());
            }

            private int checkEventTimeOrder(EventTask task1, EventTask task2) {
                return task1.getStartTime().compareTo(task2.getStartTime());
            }

            private int checkDescriptionOrder(Task task1, Task task2) {
                return task1.getDescription().compareTo(task2.getDescription());
            }
        };
    }
}


