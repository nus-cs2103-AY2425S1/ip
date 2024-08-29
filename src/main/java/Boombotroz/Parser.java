package Boombotroz;
import java.io.IOException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Prints out all the tasks in task_list
     *
     * @param task_list list of all the tasks.
     */
    public void getList(TaskList task_list) {
        task_list.printAll();

    }

    /**
     * Marks the specified task with X based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void markTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.markTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Unmarks the specified task with based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void unmarkTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.unmarkTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Deletes the specified task based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void deleteTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.deleteTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Creates TODO typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details given.
     * @throws IOException If writing to file has issue.
     */
    public void toDoTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String toDoTask = input.substring(5);

        // check if there is a task
        ui.emptyTask(toDoTask);

        Task created_task = new ToDo(false, toDoTask);
        task_list.addTaskWithMessage(created_task);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Creates DEADLINE typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details OR no deadline given.
     * @throws IOException If writing to file has issue.
     */
    public void deadlineTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String dlTaskTime = input.substring(9);

        // check if there is a task
        ui.emptyTask(dlTaskTime);

        // check if there is a deadline
        ui.emptyDeadline(dlTaskTime);

        String dlTask = dlTaskTime.split(" /by ")[0];
        String time = dlTaskTime.split(" /by ")[1];
        Task created_task = new Deadline(false, dlTask, time);
        created_task.hasDate(ui);
        task_list.addTaskWithMessage(created_task);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Creates EVENT typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details OR invalid time period given.
     * @throws IOException If writing to file has issue.
     */
    public void eventTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String eventTaskTime = input.substring(6);

        // check if there is a task
        ui.emptyTask(eventTaskTime);

        // check if there is both a start and end time
        ui.emptyStartEnd(eventTaskTime);

        String eventTask = eventTaskTime.split(" /from ")[0];
        String time_start = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[0];
        String time_end = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[1];
        Task created_task = new Event(false, eventTask,
                time_start, time_end);
        created_task.hasDate(ui);
        task_list.addTaskWithMessage(created_task);
        storage.writeTasks(task_list.getAll());

    }

}
