package colress.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import colress.task.Task;

/**
 * Represents the ColressTaskList of the Colress chatbot.
 */
public final class ColressTaskList extends TaskList {

    public ColressTaskList() {
        super(new ArrayList<>());
    }

    public ColressTaskList(List<Task> tasks) {
        super(tasks);
    }

    /**
     * Facilitates adding the provided Task object.
     *
     * @return A string representation of the task that was added.
     */
    @Override
    public String addTask(Task task) {
        getTasks().add(task);
        return getCurrTask(getTasks().size() - 1);
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as done.
     *
     * @return A string representation of the task that was marked done.
     */
    @Override
    public String checkTask(int... taskNumbers) {
        return processTask(Task::check, taskNumbers);
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as not done.
     *
     * @return A string representation of the task that was marked not done.
     */
    public String uncheckTask(int... taskNumbers) {
        return processTask(Task::uncheck, taskNumbers);
    }

    private String processTask(Consumer<Task> action, int... taskNumbers) {
        try {
            String result = "";
            for (int i: taskNumbers) {
                Task task = getTasks().get(i - 1);
                action.accept(task);
                result += getCurrTask(i - 1);
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
        }
    }

    /**
     * Facilitates removing the Task object that corresponds to the provided task number.
     */
    @Override
    public void deleteTask(int... taskNumbers) {
        try {
            for (int i = taskNumbers.length - 1; i >= 0; i--) {
                assert !isOutOfBounds(taskNumbers[i]) : "Task Number should not be out of bounds.";
                int currIndex = taskNumbers[i] - 1;
                getTasks().remove(currIndex);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
