package boombotroz;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Prints out all the tasks in task_list
     *
     * @param taskList list of all the tasks.
     */
    public String printList(Ui ui, TaskList taskList) {
        Collections.sort(taskList.getTaskList(), Comparator.comparingInt(Task::getPriority).reversed());
        return ui.printAll(taskList);
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
     * @return Message after task is marked.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public String markTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {
        String s = taskList.markTask(input, ui);
        storage.writeTasks(taskList.getAll());
        return s;
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
     * @return Message after task is unmarked.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public String unmarkTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {
        String s = taskList.unmarkTask(input, ui);
        storage.writeTasks(taskList.getAll());
        return s;
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
     * @return Message after task is deleted.
     * @throws BoomException If position not given OR position out of range.
     * @throws IOException If writing to file has issue.
     */
    public String deleteTask(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {
        String s = taskList.deleteTask(input, ui);
        storage.writeTasks(taskList.getAll());
        return s;
    }

    /**
     * Returns the task(s) that contains the word from the input.
     * If no such word, will throw exception.
     *
     * @param taskList list of all the tasks.
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @return Message of task(s) matching word.
     * @throws BoomException If position not given OR position out of range.
     *
     */
    public String findTask(TaskList taskList, String input, Ui ui) throws BoomException {
        ui.hasKeyWord(input);
        String word = input.substring(5);
        return ui.findTaskMessage(taskList, word);
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
     * @return Message after creating and adding task.
     * @throws BoomException If no task details given.
     * @throws IOException If writing to file has issue.
     */
    public String createToDo(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {
        // check if there is a task
        String toDoTask = input.substring(5);
        ui.hasTask(toDoTask);
        //checks if there is a priority
        ui.hasCorrectPriority((toDoTask));
        String[] parts = toDoTask.split(" /prior ");
        String description = parts[0];

        int priority = Integer.parseInt(parts[1]);
        Task createdTask = new ToDo(false, description, priority);
        taskList.addTask(createdTask);
        storage.writeTasks(taskList.getAll());

        String s = ui.createTaskMessage(createdTask, taskList);
        return s;

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
     * @return Message after creating and adding task.
     * @throws BoomException If no task details OR no deadline given.
     * @throws IOException If writing to file has issue.
     */
    public String createDeadline(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {
        // check if there is a task
        String dlTaskTime = input.substring(9);
        ui.hasTask(dlTaskTime);
        // check if there is a deadline
        ui.hasEnd(dlTaskTime);
        //checks if there is a priority
        ui.hasCorrectPriority((dlTaskTime));

        int priority = Integer.parseInt(dlTaskTime.split(" /prior ")[1]);
        String dlTask = dlTaskTime.split(" /by ")[0];
        String time = dlTaskTime.split(" /by ")[1].split(" /prior ")[0];

        Task createdTask = new Deadline(false, dlTask, time, priority);
        createdTask.hasDate(ui);
        taskList.addTask(createdTask);
        storage.writeTasks(taskList.getAll());

        String s = ui.createTaskMessage(createdTask, taskList);
        return s;
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
     * @return Message after adding task.
     * @throws BoomException If no task details OR invalid time period given.
     * @throws IOException If writing to file has issue.
     */
    public String createEvent(TaskList taskList, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        // check if there is a task
        String eventTaskTime = input.substring(6);
        ui.hasTask(eventTaskTime);
        // check if there is both a start and end time
        ui.hasStartEnd(eventTaskTime);
        //checks if there is a priority
        ui.hasCorrectPriority((eventTaskTime));

        int priority = Integer.parseInt(eventTaskTime.split(" /prior ")[1]);
        String eventTask = eventTaskTime.split(" /from ")[0];
        String timeStart = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[0];
        String timeEnd = eventTaskTime.split(" /from ")[1]
                .split(" /to ")[1]
                .split(" /prior ")[0];

        Task createdTask = new Event(false, eventTask,
                timeStart, timeEnd, priority);
        createdTask.hasDate(ui);
        taskList.addTask(createdTask);
        storage.writeTasks(taskList.getAll());

        String s = ui.createTaskMessage(createdTask, taskList);
        return s;
    }
}
