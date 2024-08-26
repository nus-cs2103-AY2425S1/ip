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
