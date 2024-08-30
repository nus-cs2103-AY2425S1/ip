public abstract class Task {
    protected String taskDesc;
    protected boolean isDone;

    Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String markAsDone() throws ConverSageException {
        if (this.isDone) {
            throw new ConverSageException("This task is already marked as done...");
        }
        this.isDone = true;
        return "[X] " + this.taskDesc;
    }

    public String markAsUndone() throws ConverSageException {
        if (!this.isDone) {
            throw new ConverSageException("This task is already marked as not done...");
        }
        this.isDone = false;
        return "[ ] " + this.taskDesc;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public abstract String toFileFormat();

    public static Task fromFileFormat(String fileFormatString) throws ConverSageException{
        // Note: 2 escape char used
        String[] parts = fileFormatString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("Done");
        String taskDesc = parts[2];

        switch (taskType) {
            case "ToDo":
                Task newTodo = new ToDo(taskDesc);
                if (isDone) {
                    newTodo.markAsDone();
                }
                return newTodo;

            case "Event":
                Task newEvent = new Event(taskDesc, parts[3], parts[4]);
                if (isDone) {
                    newEvent.markAsDone();
                }
                return newEvent;

            case "Deadline":
                Task newDeadline = new Deadline(taskDesc, parts[3]);
                if (isDone) {
                    newDeadline.markAsDone();
                }
                return newDeadline;

            default:
                throw new ConverSageException("Unknown Task Type: " + taskType);
        }
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.taskDesc;
    }
}
