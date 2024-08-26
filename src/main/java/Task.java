public class Task {
    private final String title;
    private boolean status;

    public Task(String title) {
        this.title = title;
        this.status = false;
    }

    public static Task of(String info, TaskType type) throws InsufficientInfoException {
        return switch (type) {
            case TODO -> new Todo(info);
            case EVENT -> {
                String[] split = info.split(" /from ");
                if (split.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                String[] from_to = split[1].split(" /to ");
                if (from_to.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                yield new Event(split[0], from_to[0], from_to[1]);
            }
            case DEADLINE -> {
                String[] split = info.split(" /by ");
                if (split.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                yield new Deadline(split[0], split[1]);
            }
        };
    }

    public String getTitle() {
        return this.title;
    }

    public void markAsDone() {
        this.status = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString());
    }

    public void markAsUndone() {
        this.status = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this.toString());
    }

    public String getStatusIndicator() {
        return (status ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIndicator() + this.getTitle();
    }
}