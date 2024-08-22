public class Task {
    private String name;
    private Boolean completed;

    public Task (String name) {
        this.name = name;
        this.completed = false;
    }

    public void setCompleted(Boolean completed) {
        if (!this.completed && completed) {
            this.completed = true;
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(this);
            System.out.println("____________________________________________________________");

        } else if (this.completed && !completed) {
            this.completed = false;
            System.out.println("____________________________________________________________");
            System.out.println("Ok, I've marked this task as not done yet: ");
            System.out.println(this);
            System.out.println("____________________________________________________________");

        } else if (this.completed && completed) {
            System.out.println("____________________________________________________________");
            System.out.println("This task is already completed: ");
            System.out.println(this);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("This task is not completed: ");
            System.out.println(this);
            System.out.println("____________________________________________________________");
        }
    }

    @Override
    public String toString() {
        return this.completed ? "[X] " + this.name : "[ ] " + this.name;
    }
}
