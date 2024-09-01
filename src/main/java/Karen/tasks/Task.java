package Karen.tasks;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String cross = this.isDone ? "X" : " ";
        return String.format("[%s] %s", cross, this.name);
    }

    /**
     * Returns a readable string to be written into Karen.txt for storage
     *
     */
    public abstract String toFileString();


    /**
     *
     * @param s String containing text to parse into a Task
     * @return A <code>Task</code> corresponding to the parsed text
     * @throws IllegalArgumentException if input string has invalid format
     */
    public static Task fromFileString(String s) throws IllegalArgumentException{
        String[] params = s.split(" \\| ");
        String type = params[0];

        Task newTask = null;
        boolean isMarked = params[1].equals("1");
        String name = params[2];
        switch (type) {
        case "T":
            newTask = new Todo(name);
            break;
        case "D":
            String dueDate = params[3];
            newTask = new Deadline(name, dueDate);
            break;
        case "E":
            String from = params[3];
            String to = params[4];
            newTask = new Event(name, from, to);
            break;
        default:
            throw new IllegalArgumentException();
        }

        if (isMarked) {
            newTask.mark();
        }

        return newTask;
    }
}
