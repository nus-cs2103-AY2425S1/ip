abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    protected Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public static Task of(String taskObjectString) {
        if (taskObjectString.startsWith("[T]")) {
            boolean isDone = taskObjectString.charAt(4) == 'X';
            String taskDescription = taskObjectString.substring(7);

            return new ToDo(taskDescription, isDone);

        } else if (taskObjectString.startsWith("[D]")) {
            boolean isDone = taskObjectString.charAt(4) == 'X';
            int byIndex = taskObjectString.indexOf("by");

            String taskDescription = taskObjectString.substring(7, byIndex - 2);
            String deadline = taskObjectString.substring(byIndex + 4, taskObjectString.length() - 1);

            return new Deadline(taskDescription, deadline, isDone);

        } else if (taskObjectString.startsWith("[E]")) {
            boolean isDone = taskObjectString.charAt(4) == 'X';
            int fromIndex = taskObjectString.indexOf("from");
            int toIndex = taskObjectString.indexOf("to");

            String taskDescription = taskObjectString.substring(7, fromIndex - 2);
            String from = taskObjectString.substring(fromIndex + 6, toIndex - 1);
            String to = taskObjectString.substring(toIndex + 4, taskObjectString.length() - 1);

            return new Event(taskDescription, from, to, isDone);
        } else {
            throw new IllegalArgumentException("Task object string is not a task object.");
        }
    }

    private boolean isDone() {
        return this.isDone;
    }

    private void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.setDone(true);
    }

    public void unmarkAsDone() {
        this.setDone(false);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + taskDescription;
    }
}
