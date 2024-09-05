package boombotroz;
import java.io.IOException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Prints out all the tasks in task_list
     *
     * @param taskList list of all the tasks.
     */
    public void getList(TaskList taskList) {
        taskList.printAll();

    }

    /**
     * Marks the specified task with X based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void markTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        taskList.markTask(input, ui);
        storage.writeTasks(taskList.getAll());

    }

    /**
     * Unmarks the specified task with based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void unmarkTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        taskList.unmarkTask(input, ui);
        storage.writeTasks(taskList.getAll());

    }

    /**
     * Deletes the specified task based on position given by input.
     * Stores the task into txt file.
     * If the position is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public void deleteTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        taskList.deleteTask(input, ui);
        storage.writeTasks(taskList.getAll());

    }

    /**
     * Find the task(s) that contains the word from the input.
     * If no such word, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     *
     */
    public void findTask(TaskList taskList, String input, Ui ui) throws BoomException {
        ui.emptyWord(input);
        String word = input.substring(5);
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.getTask(i);
            if (curr.getDescription().matches(word)) {
                System.out.println(String.format("%d.%s", i, curr));
            }
        }
    }

    /**
     * Creates TODO typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details given.
     * @throws IOException If writing to file has issue.
     */
    public void toDoTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String toDoTask = input.substring(5);

        // check if there is a task
        ui.emptyTask(toDoTask);

        Task createdTask = new ToDo(false, toDoTask);
        taskList.addTaskWithMessage(createdTask);
        storage.writeTasks(taskList.getAll());

    }

    /**
     * Creates DEADLINE typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details OR no deadline given.
     * @throws IOException If writing to file has issue.
     */
    public void deadlineTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String dlTaskTime = input.substring(9);

        // check if there is a task
        ui.emptyTask(dlTaskTime);

        // check if there is a deadline
        ui.emptyDeadline(dlTaskTime);

        String dlTask = dlTaskTime.split(" /by ")[0];
        String time = dlTaskTime.split(" /by ")[1];
        Task createdTask = new Deadline(false, dlTask, time);
        createdTask.hasDate(ui);
        taskList.addTaskWithMessage(createdTask);
        storage.writeTasks(taskList.getAll());

    }

    /**
     * Creates EVENT typed task.
     * Stores the task into txt file.
     * If the input is invalid, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param storage file that stores current state of task_list.
     * @param ui handles errors that may occur.
     * @throws BoomException If no task details OR invalid time period given.
     * @throws IOException If writing to file has issue.
     */
    public void eventTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        String eventTaskTime = input.substring(6);

        // check if there is a task
        ui.emptyTask(eventTaskTime);

        // check if there is both a start and end time
        ui.emptyStartEnd(eventTaskTime);

        String eventTask = eventTaskTime.split(" /from ")[0];
        String timeStart = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[0];
        String timeEnd = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[1];
        Task createdTask = new Event(false, eventTask,
                timeStart, timeEnd);
        createdTask.hasDate(ui);
        taskList.addTaskWithMessage(createdTask);
        storage.writeTasks(taskList.getAll());

    }

}
