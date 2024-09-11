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
 * Models after a command that shows a sorted full list of task.
 */
public class SortCommand extends Command {
    SortCommand() {
        super(CommandVerb.SORT);
    }

    /**
     * Executes the SortCommand which lists out all the tasks stored in the list of tasks.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The list of tasks stored after command execution in the bot's GUI.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Comparator<Task> comparator = generateComparator();
        try {
            tasks.sort(comparator); // mutation side effect
            storage.saveToFile(tasks);
            String tasksInString = tasks.toString();
            return ui.showSorted(tasksInString);
        } catch (IOException e) { // from saveToFile() in Storage class
            return "Failed to execute Command:\nProblems creating an instance of FileWriter";
        }
    }

    /**
     * Anonymous class
     * @return Comparator class.
     */
    private Comparator<Task> generateComparator() {
        return new Comparator<Task>() {
            private final static int DONE_PRIORITY = 0;
            private final static int NOT_DONE_PRIORITY = 1;

            private final static int TODO_PRIORITY = 3;
            private final static int DEADLINE_PRIORITY = 2;
            private final static int EVENT_PRIORITY = 1;

            private int rankIsDone(Task task) {
                if (task.isDone()) {
                    return DONE_PRIORITY;
                } else {
                    assert !task.isDone();
                    return NOT_DONE_PRIORITY;
                }
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

            @Override
            public int compare(Task task1, Task task2) {
                int task1DoneRank = rankIsDone(task1);
                int task2DoneRank = rankIsDone(task2);
                if (task1DoneRank != task2DoneRank) {
                    return task2DoneRank - task1DoneRank;
                }

                int task1TaskTypeRank = rankTaskType(task1.showTaskType());
                int task2TaskTypeRank = rankTaskType(task2.showTaskType());
                if (task1TaskTypeRank != task2TaskTypeRank) {
                    return task2TaskTypeRank - task1TaskTypeRank;
                }

                final int SAME_ORDER = 0;
                int relativeOrder = 0;
                if (task1 instanceof DeadlineTask deadlineTask1 && task2 instanceof DeadlineTask deadlineTask2) {
                    relativeOrder = deadlineTask1.getDeadline().compareTo(deadlineTask2.getDeadline());
                } else if (task1 instanceof EventTask eventTask1 && task2 instanceof EventTask eventTask2) {
                    relativeOrder = eventTask1.getStartTime().compareTo(eventTask2.getStartTime());
                }
                if (relativeOrder != SAME_ORDER) {
                    return relativeOrder ;
                }
                return task1.getDescription().compareTo(task2.getDescription());
            }
        };
    }
}


