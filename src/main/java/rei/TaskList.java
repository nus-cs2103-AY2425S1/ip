package rei;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public List<Task> getListOfTasks() {
        return listOfTasks;
    }

    public void printTasks() {
        String taskList = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            taskList += String.format("%d. %s\n", i + 1, listOfTasks.get(i).toString());
        }
        Ui.print("Here are the tasks in your list: \n" + taskList);
    }

    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
        Ui.print("Got it. I've added this task:\n" +
                "    " + getTask(getNumOfTasks() - 1) + "\n" +
                String.format("Now you have %d tasks in the list.", getNumOfTasks()));
    }

    public void markTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            listOfTasks.get(index - 1).markAsDone();
            Ui.print("Okay! I've marked this task as done:\n" + getTask(index - 1));
        } else {
            Ui.print("No task found. Please retry!");
        }
    }

    public void unmarkTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            listOfTasks.get(index - 1).markAsNotDone();
            Ui.print("Okay! I've marked this task as not done yet:\n" + getTask(index - 1));
        } else {
            Ui.print("No task found. Please retry!");
        }
    }

    public void deleteTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            Task removed = listOfTasks.remove(index - 1);
            Ui.print("Okay! I've deleted this task :\n" +
                    removed + "\n" +
                    String.format("Now you have %d tasks in the list.", getNumOfTasks()));
        } else {
            Ui.print("No task found. Please retry!");
        }
    }

    public String toStoringFormat() {
        String tasksInStoringFormat = "";
        for (int i = 0; i < this.getNumOfTasks(); i++) {
            tasksInStoringFormat += this.getTask(i).toStoringFormat() + "\n";
        }
        return tasksInStoringFormat;
    }

}
