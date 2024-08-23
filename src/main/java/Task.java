public class Task {

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    final String desc;
    boolean isDone;

    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    void markDone() {
        this.isDone = true;
    }

    void markNotDone() {
        this.isDone = false;
    }

    String getType() {
        return "[ ]";
    }

    @Override
    public String toString() {
        return getType() + (isDone ? "[X] " : "[ ] ") + desc;
    }

    static class ToDo extends Task {
        ToDo(String desc) {
            super(desc);
        }

        @Override
        String getType() {
            return "[T]";
        }
    }

    static class Deadline extends Task {
        String by;

        Deadline(String desc, String by) {
            super(desc);
            this.by = by;
        }

        @Override
        String getType() {
            return "[D]";
        }

        @Override
        public String toString() {
            return super.toString() + " (by: " + by + ")";
        }
    }

    static class Event extends Task {
        String from;
        String to;

        Event(String desc, String from, String to) {
            super(desc);
            this.from = from;
            this.to = to;
        }

        @Override
        String getType() {
            return "[E]";
        }

        @Override
        public String toString() {
            return super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

}
