package karen.tasks;

/**
 * Abstract class Task which keeps track of its name and whether it is marked as complete
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor to initialize the Task's name
     * @param name String representing the Task name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Sets the value of isDone field to true
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the value of isDone field to false
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the value of this.isDone
     *
     */
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
     * Returns a <code>Task</code> from parsing a String retrieved from Karen.txt
     * @param s A String to be parsed
     * @return <code>Task</code> corresponding to the parsed String
     * @throws IllegalArgumentException if the String is not in the correct format
     */
    public static Task fromFileString(String s) throws IllegalArgumentException {
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
