public class TaskTracker {
    private Task[] taskList;
    private int counter;

    public TaskTracker() {
        this.taskList = new Task[100];
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
                if (this.taskList[i].getIsDone() == false) {
                    System.out.println("" + (i + 1) + ". [ ] " + this.taskList[i]);
                } else {
                    System.out.println("" + (i + 1) + ". [X] " + this.taskList[i]);
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
            this.taskList[z].mark();
        }
    }

    public void unmarkDone(int q) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
        } else if (q < 0 || q >= this.counter) {
            throw new InvalidIndexException("You have entered an invalid task number! Please try again.");
        }
        else {
            this.taskList[q].unmark();
        }
    }

    public void updateListToDo(String s) {
        taskList[this.counter] = new ToDo(s);
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(taskList[this.counter - 1]);
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
    }

    public void updateListDeadline(String s, String t) {
        taskList[this.counter] = new Deadline(s, t);
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(taskList[this.counter - 1]);
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
    }

    public void updateListEvent(String s, String t, String u) {
        taskList[this.counter] = new Event(s, t, u);
        this.counter++;
        System.out.println("Gotcha!! Added this task to your list:");
        System.out.println(taskList[this.counter - 1]);
        System.out.println("Take note you currently have " + this.counter + " task/s on your list. You got this Champ!!!");
    }
}