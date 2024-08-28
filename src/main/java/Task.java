public abstract class Task {
    private String task;
    private boolean isDone;

    private Task(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String printTask() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }

    public static ToDo createToDo(String task) {
        return new ToDo(task);
    }

    public static Deadline createDeadline(String task, String deadline) {
        return new Deadline(task, deadline);
    }

    public static Event createEvent(String task, String from, String to) {
        return new Event(task, from, to);
    }

    private static class ToDo extends Task {
        private ToDo(String task) {
            super(task);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", this.printTask());
        }
    }

    private static class Deadline extends Task {
        private String deadline;
        private Deadline(String task, String deadline) {
            super(task);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by: %s)", this.printTask(), this.deadline);
        }
    }

    private static class Event extends Task {
        private String from;
        private String to;
        private Event(String task, String from, String to) {
            super(task);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("[E]%s(from: %sto: %s)", this.printTask(), this.from, this.to);
        }
    }

}
