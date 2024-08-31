package murphy.task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import murphy.MurphyException;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(Scanner scanner) throws MurphyException {
        tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String nextTask = scanner.nextLine();
            String[] taskDetails = nextTask.split("\\|");
            if (Objects.equals(taskDetails[0], "T") && taskDetails.length == 3) {
                Task task = new Todo(taskDetails[2], Boolean.parseBoolean(taskDetails[1]));
                tasks.add(task);
            } else if (Objects.equals(taskDetails[0], "D") && taskDetails.length == 4) {
                Task task = new Deadline(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                        taskDetails[3]);
                tasks.add(task);
            } else if (Objects.equals(taskDetails[0], "E") && taskDetails.length == 5) {
                Task task = new Event(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                        taskDetails[3], taskDetails[4]);
                tasks.add(task);
            } else {
                throw new MurphyException("Save file seems to be corrupted. Overriding save.");
            }
        }
    }

    public String addItem(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task, tasks.size());
    }

    public String deleteItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("The task number you chose is out of the range of tasks!");
        }
        Task task = tasks.remove(index - 1);
        return String.format("Got it. I've deleted this task:\n%s\nNow you have %d task(s) in the list.",
                task, tasks.size());
    }

    public String markItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("Index is outside the range of tasks!");
        }
        tasks.get(index - 1).mark();
        return String.format("Nice! I've marked this task as done:\n%s", tasks.get(index - 1));
    }

    public String unmarkItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("Index is outside the range of tasks!");
        }
        tasks.get(index - 1).unmark();
        return String.format("Ok, I've unmarked this task. Guess Murphy struck?\n%s", tasks.get(index - 1));
    }

    public String toSaveString() {
        int sz = tasks.size();
        if (sz == 0) {
            return "Your list is currently empty. Add some tasks to get started!";
        }
        StringBuilder string = new StringBuilder();
        for (Task task : tasks) {
            string.append(String.format("%s\n", task.toSaveString()));
        }
        string.deleteCharAt(string.length() - 1); //remove the last newline
        return string.toString();
    }

    @Override
    public String toString() {
        int sz = tasks.size();
        if (sz == 0) {
            return "Your list is currently empty. Add some tasks to get started!";
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < sz; i++) {
            string.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        string.deleteCharAt(string.length() - 1); //remove the last newline
        return string.toString();
    }

}

