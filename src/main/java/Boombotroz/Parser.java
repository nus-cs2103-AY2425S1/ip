package Boombotroz;
import java.io.IOException;

public class Parser {

    public void getList(TaskList task_list) {
        task_list.printAll();

    }

    public void markTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.markTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    public void unmarkTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.unmarkTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    public void deleteTask(TaskList task_list, String input,
            Storage storage, Ui ui)
                    throws BoomException, IOException {

        task_list.deleteTask(input, ui);
        storage.writeTasks(task_list.getAll());

    }

    /**
     * Find the task(s) that contains the word from the input.
     * If no such word, will throw exception.
     *
     * @param task_list list of all the tasks.
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     *
     */
    public void findTask(TaskList task_list, String input, Ui ui) throws BoomException {
        ui.emptyWord(input);
        String word = input.substring(5);
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i< task_list.size(); i++) {
            Task curr = task_list.getTask(i);
            if (curr.getDescription().matches(word)) {
                System.out.println(String.format("%d.%s", i, curr));
            }
        }
    }

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
