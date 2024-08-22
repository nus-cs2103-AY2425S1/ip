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
                    System.out.println("" + (i + 1) + ". [ ] " + this.taskList[i].getName());
                } else {
                    System.out.println("" + (i + 1) + ". [X] " + this.taskList[i].getName());
                }

            }
        }
    }

    public void markDone(int z) {
        if (this.counter == 0) {
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
        } else if (z < 0 || z >= this.counter) {
            System.out.println("You have entered an invalid task number! Please try again.");
        }
        else {
            this.taskList[z].mark();
        }
    }

    public void unmarkDone(int q) {
        if (this.counter == 0) {
            System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
        } else if (q < 0 || q >= this.counter) {
            System.out.println("You have entered an invalid task number! Please try again.");
        }
        else {
            this.taskList[q].unmark();
        }
    }

    public void updateList(String s) {
        taskList[this.counter] = new Task(s);
        this.counter++;
        System.out.println("added " + s);
    }
}