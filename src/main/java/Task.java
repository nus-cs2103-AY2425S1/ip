public class Task {

    public String description;
    public boolean taskIsDone;

    public Task(String description) {
        this.description = description;
        this.taskIsDone = false;
    }

    @Override
    public String toString() {
        return taskIsDone ? "[X] " + this.description : "[ ] " + this.description;
    }


    public void toggleTaskDone() {
        if (this.taskIsDone) {
            System.out.println("OK, I've marked this task as not done yet:");
        } else {
            System.out.println("Nice! I've marked this task as done:");
        }
        this.taskIsDone = !this.taskIsDone;
        System.out.println(this.toString());


    }

}
