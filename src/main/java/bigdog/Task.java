package bigdog;

public abstract class Task {

    private boolean marked;
    private String taskRep;

    private static final String UNRECOGNIZED_KEYWORDS_MESSAGE =
            "Sorry the only keywords I recognise are:\n" +
                    "1. todo\n" +
                    "2. deadline\n" +
                    "3. event\n" +
                    "4. mark\n" +
                    "5. unmark\n" +
                    "6. delete\n" +
                    "7. list\n" +
                    "8. bye";

    public Task(String taskRep, boolean marked) {
        this.marked = marked;
        this.taskRep = taskRep;
    }

    public static Task of(String task, boolean marked) throws BigdogException {
        if (task == null || task.isEmpty()) {
            throw new BigdogException("Task cannot be empty!");
        }
        if (task.startsWith("T")) {
            return Todo.of(task, marked);
        } else if (task.startsWith("D")) {
            return Deadline.of(task, marked);
        } else if (task.startsWith("E")) {
            return Event.of(task, marked);
        } else {
            throw new BigdogException(UNRECOGNIZED_KEYWORDS_MESSAGE);
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
