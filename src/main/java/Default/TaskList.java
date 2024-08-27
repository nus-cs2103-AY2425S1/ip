package Default;

import java.util.ArrayList;

import Exceptions.NedException;
import Tasks.Task;
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void removeTask(int index) throws NedException {
        try {
            Task selectedTask = getTask(index);
            this.listOfTasks.remove(index);
            Ui.print("Noted m'lord. The following task has been removed:");
            Ui.print(Ui.INDENTATIONS + selectedTask);
            Ui.print(String.format("Now you've %d tasks in the list. Get to it then.", listOfTasks.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }

    private Task getTask(int index) throws NedException {
        try {
            return this.listOfTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }
    public void addTask(Task newTask){
        this.listOfTasks.add(newTask);
        Ui.print("Aye, I've added this task m'lord:");
        Ui.print(Ui.INDENTATIONS + newTask);
        Ui.print("Now you've " + this.listOfTasks.size() + " tasks left. Get to it then!");
    }

    public void listTasks() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            String task = Ui.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfTasks.get(i));
            Ui.print(task);
        }
    }

    public void markTaskAsDone(int index) throws NedException{
        Task selectedTask = getTask(index);
        selectedTask.markAsDone();
        Ui.print("Good work. One down, more to go!");
        Ui.print(selectedTask.toString());
    }
    public void markTaskAsUndone(int index) throws NedException{
        Task selectedTask = getTask(index);
        selectedTask.markAsNotDone();
        Ui.print("Oh no. One back up, more to go!");
        Ui.print(selectedTask.toString());
    }

    public int getSize() {
        return this.listOfTasks.size();
    }

    public String getTaskTextForm(int index) throws NedException{
        return getTask(index).toTextForm();
    }


}
