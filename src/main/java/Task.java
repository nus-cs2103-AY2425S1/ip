import java.lang.StringBuilder;

public class Task {
    private static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }
    }

    private static class Deadline extends Task {
        private String dueDate;

        public Deadline(String name, String dueDate) {
            super(name);
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return String.format("[D]%s (by %s)",
                    super.toString(),
                    dueDate);
        }
    }

    private static class Event extends Task {
        private String startDate;
        private String endDate;

        public Event(String name, String startDate, String endDate) {
            super(name);
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from %s to %s)",
                    super.toString(),
                    startDate,
                    endDate);
        }
    }

    protected String name;
    protected boolean isDone;

    private Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public static Task of(String command) throws UnknownCommandException,
            EmptyTaskDescriptionException, MissingTokenException{
        String[] tokens = command.split(" ");

        String taskType = tokens[0];
        if (!taskType.equalsIgnoreCase("todo")
        && !taskType.equalsIgnoreCase("deadline")
        && !taskType.equalsIgnoreCase("event")) {
            throw new UnknownCommandException(taskType);
        }

        if (tokens.length < 2) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        StringBuilder taskName = new StringBuilder();
        if (taskType.equalsIgnoreCase("todo")) {
            for (int i = 1; i < tokens.length; i++) {
                taskName.append(tokens[i]);
                if (i < tokens.length - 1) {
                    taskName.append(" ");
                }
            }
            return new ToDo(taskName.toString());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            int byTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/by")) {
                    byTokenIndex = i;
                }
            }

            if (byTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/by");
            }

            for (int i = 1; i < byTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < byTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder dueDate = new StringBuilder();
            for (int i = byTokenIndex + 1; i <tokens.length; i++) {
                dueDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDate.append(" ");
                }
            }

            return new Deadline(taskName.toString(), dueDate.toString());
        } else if (taskType.equalsIgnoreCase("event")) {
            int fromTokenIndex = -1;
            int toTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/from")) {
                    fromTokenIndex = i;
                }
                if (tokens[i].equalsIgnoreCase("/to")) {
                    toTokenIndex = i;
                }
            }

            if (fromTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/from");
            }

            if (toTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/to");
            }

            for (int i = 1; i < fromTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < fromTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder startDate = new StringBuilder();
            for (int i = fromTokenIndex + 1; i < toTokenIndex; i++) {
                startDate.append(tokens[i]);
                if (i < toTokenIndex - 1) {
                    startDate.append(" ");
                }
            }

            StringBuilder endDate = new StringBuilder();
            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDate.append(" ");
                }
            }
            return new Event(taskName.toString(),
                    startDate.toString(),
                    endDate.toString());
        } else {
            throw new UnknownCommandException(taskType);
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                getStatusIcon(),
                this.name);
    }
}
