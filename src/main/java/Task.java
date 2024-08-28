public abstract class Task {
    public String name;
    public boolean isDone;

    public Task(String name) {
        this.name =name;
        this.isDone = false;
    }

    public abstract String toFileText();

    public static Task fromFileText(String fileFormat) throws Exception {
        String[] text = fileFormat.split(" \\| ");
        String taskType = text[0];
        boolean isDone = text[1].equals("1");
        String name = text[2];

        Task task;
        if (taskType.equals("T")) {
            task = new ToDo(name);
        } else if (taskType.equals("D")) {
            String deadline = text[3];
            task = new Deadline(name, deadline);
        } else if (taskType.equals("E")) {
            String from = text[3];
            String to = text[4];
            task = new Event(name, from, to);
        } else {
            throw new Exception("Invalid task type");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + name : "[ ] " + name;
    }
}
