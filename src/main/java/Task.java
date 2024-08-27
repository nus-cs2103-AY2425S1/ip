import java.text.Normalizer;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws TaskArgumentMissingException {
        if (description == null || description.isEmpty()) {
            throw new TaskArgumentMissingException(getEmptyDescriptionErrorMessage());
        }
        this.description = description;
        isDone = false;
    }

    public static Task getTaskFromSave(String taskSaveString) {
        try {
            taskSaveString = taskSaveString.trim(); // removes extra new lines
            String[] args = taskSaveString.split("(\\s\\|\\s)"); // splits string along < | > delimiter

            String type = args[0];
            boolean isDone = args[1].equals("1");
            Task task = switch (type) {
                case "T" -> new TodoTask(args[2]);
                case "D" -> new DeadlineTask(args[2], args[3]);
                case "E" -> new EventTask(args[2], args[3], args[4]);
                default -> null;
            };

            if (task != null) {
                task.isDone = isDone;
            }

            return task;

        } catch (IndexOutOfBoundsException | TaskArgumentMissingException e) {
            FormatPrinter.printIndent(
                    String.format(
                            "Warning: <%s> is missing required arguments, ignoring task.",
                            taskSaveString),
                    Ekud.OUTPUT_PREFIX);
            return null;
        }
    }

    public abstract String getEmptyDescriptionErrorMessage();

    public String getSaveTaskString() {
        int statusInt = (isDone ? 1 : 0);
        return String.format("%d | %s", statusInt, description);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        // formats task as "[statusIcon] description"
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
