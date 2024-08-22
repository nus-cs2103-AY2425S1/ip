public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of(String text) {
        String action = text.split(" ")[0];
        String description = getDescription(text);;
        return switch (action) {
            case "todo" -> new Todo(description);
            case "deadline" -> new Deadline(description, Task.findArgumentOf("by", text));
            case "event" -> new Event(description,
                    Task.findArgumentOf("from", text),
                    Task.findArgumentOf("to", text));
            default -> new Todo(text);
        };
    }

    private static String findArgumentOf(String parameter, String text) {
        String[] fragments = text.split("/");
        for (int i = 1; i < fragments.length; i++) {
            String[] args = fragments[i].split(" ");
            if (args[0].equals(parameter)) {
                args[0] = "";
                return String.join(" ", args).substring(1);
            }
        }
        return "MISSING ARGUMENTS";
    }

    private static String getDescription(String text) {
        String[] arr = text.split("/")[0].split(" ");
        arr[0] = "";
        return String.join(" ", arr).substring(1);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}