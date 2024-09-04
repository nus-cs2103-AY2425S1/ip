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
        return new ArrayList<>(this.list);
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

    /**
     * Finds and returns tasks that contain the specified search string.
     *
     * @param str The string to search for in the task descriptions.
     * @return A string representing the tasks that match the search string with their indices.
     */
    public String find(String str) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().contains(str)) {
                result.append(i + 1).append(". ").append(this.list.get(i).toString()).append("\n");
            }
        }
        if (result.toString().equals("Here are the tasks in your list:\n")) {
            return "There are no similar tasks in your list!\n";
        }
        return result.toString();
    }

    public void show() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < this.list.size(); i++) {
            listString.append(i + 1).append(".").append(this.list.get(i)).append("\n");
        }

        return listString.toString();
    }
}