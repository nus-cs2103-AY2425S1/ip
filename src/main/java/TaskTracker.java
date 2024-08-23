import java.util.*;

public class TaskTracker {
    private ArrayList<Task> taskList;
    private int counter;

    public TaskTracker() {
        this.taskList = new ArrayList<Task>();
        this.counter = 0;
    }
    public int getCounter() {
        return this.counter;
    }
    public void getList() {
        if (this.counter == 0) {
            System.out.println("No tasks currently!");
        } else {
            System.out.println("Here are your tasks!!! Remember to complete them!!!");
            for (int i = 0; i < this.counter; i++) {
                if (this.taskList.get(i).getIsDone() == false) {
                    System.out.println("" + (i + 1) + ". [ ] " + this.taskList.get(i));
                } else {
                    System.out.println("" + (i + 1) + ". [X] " + this.taskList.get(i));
                }

            }
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
        }
    }

    public void updateListToDo(String s) {
        this.taskList.add(new ToDo(s));
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(this.taskList.get(this.counter - 1));
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
    }

    public void updateListDeadline(String s, String t) {
        this.taskList.add(new Deadline(s, t));
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(this.taskList.get(this.counter - 1));
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
    }

    public void updateListEvent(String s, String t, String u) {
        this.taskList.add(new Event(s, t, u));
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(this.taskList.get(this.counter - 1));
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
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
}