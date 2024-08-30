public class Task {

    private boolean marked;
    private String taskRep;

    protected Task(String taskRep, boolean marked) {
        this.marked = marked;
        this.taskRep = taskRep;
    }

    public static Task of(String task) throws BigdogException {
        if (task.startsWith("todo")) {
            return Todo.of(task);
        } else if (task.startsWith("deadline")) {
            return Deadline.of(task);
        } else if (task.startsWith("event")) {
            return Event.of(task);
        } else {
            throw new BigdogException("Sorry the only keywords I recognise are \n1.todo\n2.deadline\n3.event" +
                    "\n4.mark\n5.unmark\n6.delete\n7.list\n8.bye ");
        }
    }


    public static Task of(String task, boolean marked) throws BigdogException {
        if (task.startsWith("T")) {
            return Todo.of(task, marked);
        } else if (task.startsWith("D")) {
            return Deadline.of(task, marked);
        } else if (task.startsWith("E")) {
            return Event.of(task, marked);
        } else {
            throw new BigdogException("Sorry the only keywords I recognise are \n1.todo\n2.deadline\n3.event" +
                    "\n4.mark\n5.unmark\n6.delete\n7.list\n8.bye ");
        }
    }

    public String getDescription() {
        return this.taskRep;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + taskRep;
        } else {
            return "[ ] " + taskRep;
        }
    }


}
