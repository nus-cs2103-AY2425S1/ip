public abstract class Task {
    protected String taskName;
    protected boolean isChecked;

    Task(String taskName) {
        this.taskName = taskName;
        isChecked = false;
    }

    public void mark() {
        if (!isChecked) {
            this.isChecked = true;
            System.out.println("Nice! I've marked this task as done:");
            
        } else {
            System.out.println("This task is already marked as done!");
        }

        System.out.println(this.toString());
    }

    public void unMark() {
        if (isChecked) {
            this.isChecked = false;
            System.out.println("OK, I've marked this task as not done yet:");
            
        } else {
            System.out.println("This task is already unmarked!");
        }

        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        if (isChecked) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
