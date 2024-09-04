package bigdog;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public String add(Task task) {
        this.list.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.\n",
                task.toString(), this.list.size());
    }

    public String delete(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        Task temp = this.list.get(i - 1);
        this.list.remove(i - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                temp, this.list.size());
    }

    public ArrayList<Task> get() {
        return this.list;
    }

    public String mark(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        this.list.get(i - 1).mark();
        return String.format("Nice! I've marked this task as done:\n%s\n", this.list.get(i - 1));
    }

    public String unmark(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        this.list.get(i - 1).unmark();
        return String.format("OK, I've marked this task as not done yet:\n%s\n", this.list.get(i - 1));
    }

    @Override
    public String toString() {
        String listString = "Here are the tasks in your list:\n";

        for (int i = 0; i < this.list.size(); i++) {
            listString += (i + 1) + "." + this.list.get(i) + "\n";
        }

        return listString;
    }

    public void show() {
        System.out.println(this);
    }
}
