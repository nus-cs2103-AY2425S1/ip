package james;

public abstract class Task {
    private String description;
    private Boolean marked;

    public Task(String desc, Boolean mark) throws MissingDescriptionException{
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("Looks like you left out description of the task, please try again.");
        }
        this.description = desc;
        this.marked = mark;
    }

    public void mark() {
        this.marked = true;
    }
    public void unMark() {
        this.marked = false;
    }
    public String printTask() {
        String mark = marked ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }

    public String toFileFormat() {
        return String.format("%d | %s", marked ? 1 : 0, description);
    };
}
