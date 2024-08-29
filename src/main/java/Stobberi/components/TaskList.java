package Stobberi.components;

import Stobberi.StobberiException.StobberiException;
import Stobberi.Task.Deadline;
import Stobberi.Task.Event;
import Stobberi.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    public void markTask(int number) {
        listOfTasks.get(number - 1).setDone();
        String done = "Nice! I've marked this task as done:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        Ui.displayForm(done);
    }

    public void unmarkTask(int number) {
        listOfTasks.get(number - 1).setNotDone();
        String done = "OK, I've marked this task as not done yet:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        Ui.displayForm(done);
    }

    public void displayList() {
        String list = "Here are the tasks in your list:";
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            list += "\n" + i + ". " + listOfTasks.get(i - 1);
        }
        Ui.displayForm(list);
    }

    public void delete(int number) {
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
        String done = "Noted. I've removed this task:\n" +
                "  " + temp
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        Ui.displayForm(done);
    }

    public void displayLastAdded() {
        Ui.displayForm(
                "Got it. I've added this task:\n    "
                        + listOfTasks.get(listOfTasks.size() - 1)
                        + "Now you have " + listOfTasks.size() + " in the list.");
    }
    public void filterListByDate(String date) {
        String list = "Here are the tasks in your list that you have to do on " + date + ":\n";
        int n = 1;
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            Task task = listOfTasks.get(i - 1);
            if (task instanceof Deadline deadline) {
                if (deadline.isDuring(date)) {
                    list += n + ". " + listOfTasks.get(i - 1) + "\n";
                    n++;
                }
            } else if (task instanceof Event event) {
                if (event.isDuring(date)) {
                    list += n + ". " + listOfTasks.get(i - 1) + "\n";
                    n++;
                }
            }
        }
        Ui.displayForm(list);
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
        displayLastAdded();
    }

    public void updateTaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }
}