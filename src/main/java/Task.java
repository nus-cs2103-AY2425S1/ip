import java.util.List;
import java.util.ArrayList;

public class Task {
    boolean mark;
    String task;
    static List<Task> task_list = new ArrayList<>();

    public Task(boolean mark, String task) {
        this.mark = mark;
        this.task = task;
        task_list.add(this);
    }

    public static void mark_task(String input)
            throws BoombotrozException {

        // if there is no task number to mark
        if (input.substring(5).isEmpty()) {
            throw new BoombotrozException(
                    "You can't mark nothing !!");
        }

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // if the task number given does not fall within the task list range
        if (index + 1 <= 0
                || index + 1 > task_list.size()) {
            throw new BoombotrozException(
                    "You are out of range !!");
        }

        task_list.get(index).mark = true;
        System.out.println("Nice! I've marked this as done:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));

    }

    public static void unmark_task(String input)
            throws BoombotrozException{

        // if there is no task number to unmark
        if (input.substring(7).isEmpty()) {
            throw new BoombotrozException(
                    "You can't unmark nothing !!");
        }

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // if the task number given does not fall within the task list range
        if (index + 1 <= 0
                || index + 1 > task_list.size()) {
            throw new BoombotrozException(
                    "You are out of range !!");
        }

        task_list.get(index).mark = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));
    }

    public static void delete_task(String input)
            throws BoombotrozException{

        // if there is no task number to delete
        if (input.substring(7).isEmpty()) {
            throw new BoombotrozException(
                    "You can't delete nothing !!");
        }

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // if the task number given does not fall within the task list range
        if (index + 1 <= 0
                || index + 1 > task_list.size()) {
            throw new BoombotrozException(
                    "You are out of range !!");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format(
                "  %s", task_list.get(index)));
        task_list.remove(task_list.get(index));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                task_list.size()));
    }

    public static void printAll() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < task_list.size(); i++) {
            System.out.println(String.format(
                    "%d.%s", i + 1, task_list.get(i)));
        }
    }

    @Override
    public String toString() {
        String s;
        if (this.mark == true) {
            s = String.format("[X] %s", this.task);
        } else {
            s = String.format("[ ] %s", this.task);
        }
        return s;

    }
}
