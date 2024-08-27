import java.util.*;

public class TaskTracker {
    private ArrayList<Task> taskList;
    private int counter;
    private boolean receivedInputs;

    public TaskTracker() {
        this.taskList = new ArrayList<Task>();
        this.counter = 0;
        this.receivedInputs = false;
    }
    public int getCounter() {
        return this.counter;
    }
    public boolean getReceivedInputs() {
        return this.receivedInputs;
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
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
        } else if (z < 0 || z >= this.counter) {
            throw new InvalidIndexException("You have entered an invalid task number! Please try again.");
        }
        else {
            Task newTask = this.taskList.get(z);
            newTask.mark();
            this.taskList.set(z, newTask);
            if (this.receivedInputs) {
                System.out.println("Well Done Champ! I've marked this task as done!");
            }
        }
    }

    public void unmarkDone(int q) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
        } else if (q < 0 || q >= this.counter) {
            throw new InvalidIndexException("You have entered an invalid task number! Please try again.");
        }
        else {
            Task newTask = this.taskList.get(q);
            newTask.unmark();
            this.taskList.set(q, newTask);
            if (this.receivedInputs) {
                System.out.println("I've marked this task as undone! Please remember to complete it!");
            }
        }
    }

    public void updateListToDo(String s) {
        this.taskList.add(new ToDo(s));
        this.counter++;
        if (this.receivedInputs) {
            System.out.println("Gotcha!! Added this task to your list:");
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void updateListDeadline(String s, String t) {
        this.taskList.add(new Deadline(s, t));
        this.counter++;
        if (this.receivedInputs) {
            System.out.println("Gotcha!! Added this task to your list:");
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void updateListEvent(String s, String t, String u) {
        this.taskList.add(new Event(s, t, u));
        this.counter++;
        if (this.receivedInputs) {
            System.out.println("Gotcha!! Added this task to your list:");
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
        }
    }

    public void delete(int i) throws InvalidIndexException {
        if (this.counter == 0) {
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before deleting them.");
        } else if (i < 0 || i >= this.counter) {
            throw new InvalidIndexException("You have entered an invalid task number! Please try again.");
        }
        System.out.println("Alright I have removed this task: ");
        System.out.println(this.taskList.get(i));
        this.taskList.remove(i);
        counter--;
        System.out.println("You currently have " + counter + " task/s on hand. Please remember to complete them!");
    }
    public void receivedFirstInput() {
        this.receivedInputs = true;
    }
}