package Boombotroz;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> task_list;

    public TaskList() {
        task_list = new ArrayList<>();
    }

    public int size() {
        return task_list.size();
    }

    public void addTaskWithMessage(Task task) {
        task_list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format(
                "  %s", task));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                task_list.size()));
    }

    public void addTask(Task task) {
        task_list.add(task);
    }

    public void deleteTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to delete
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));
        task_list.remove(task_list.get(index));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                task_list.size()));
    }

    public void markTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to mark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        task_list.get(index).mark = true;
        System.out.println("Nice! I've marked this as done:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));

    }

    public void unmarkTask(String input, Ui ui) throws BoomException{
        // check if there is a given task number to unmark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        task_list.get(index).mark = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));
    }

    public void printAll() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < task_list.size(); i++) {
            System.out.println(String.format(
                    "%d.%s", i + 1, task_list.get(i)));
        }
    }

    public String getAll() {
        String s = "";
        for (int i = 0; i < task_list.size(); i++) {
            s = s.concat(task_list.get(i) + "\n");
        }
        return s;
    }

}
