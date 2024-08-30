package rainy.tasks;
import java.util.*;
import rainy.database.*;
import rainy.rainyexceptions.*;

public class TaskTracker {
    private ArrayList<Task> taskList;
    private int counter;
    private boolean receivedInputs;
    private UI ui;

    public TaskTracker() {
        this.taskList = new ArrayList<Task>();
        this.counter = 0;
        this.receivedInputs = false;
        this.ui = new UI();
    }
    public int getCounter() {
        return this.counter;
    }
    public boolean getReceivedInputs() {
        return this.receivedInputs;
    }

    public void toggleReceivedInputs() {
        this.receivedInputs = !this.receivedInputs;
    }
    public String getList() {
        if (this.counter == 0) {
            return "No tasks currently!";
        } else {
            String result = "";
            result += "Here are your tasks!!! Remember to complete them!!!" + "\n";
            for (int i = 0; i < this.counter; i++) {
                if (this.taskList.get(i).getIsDone() == false) {
                    result += "" + (i + 1) + ". [ ] " + this.taskList.get(i);
                } else {
                    result += "" + (i + 1) + ". [X] " + this.taskList.get(i);
                }
                if (i != this.counter - 1) {
                    result += "\n";
                }
            }
            return result;
        }
    }

    public void markDone(int z) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (z < 0 || z >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        }
        else {
            Task newTask = this.taskList.get(z);
            newTask.mark();
            this.taskList.set(z, newTask);
            if (this.receivedInputs) {
                this.ui.markedTask();
            }
        }
    }

    public void unmarkDone(int q) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (q < 0 || q >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        }
        else {
            Task newTask = this.taskList.get(q);
            newTask.unmark();
            this.taskList.set(q, newTask);
            if (this.receivedInputs) {
                this.ui.unmarkedTask();
            }
        }
    }
    public void sortList() {
        Collections.sort(this.taskList);
        if (this.taskList.size() > 0) {
            ui.sortDone();
        }
    }
    public void updateListToDo(String s) {
        this.taskList.add(new ToDo(s));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void updateListDeadline(String s, String t) {
        this.taskList.add(new Deadline(s, t));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void updateListEvent(String s, String t, String u) {
        this.taskList.add(new Event(s, t, u));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void delete(int i) throws InvalidIndexException {
        if (this.counter == 0) {
            this.ui.noTasksBeforeDelete();
        } else if (i < 0 || i >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            this.ui.removedTask();
            System.out.println(this.taskList.get(i));
            this.taskList.remove(i);
            counter--;
            System.out.println("You currently have " + counter + " task/s on hand. Please remember to complete them!");
        }

    }
    public void receivedFirstInput() {
        this.receivedInputs = true;
    }
}