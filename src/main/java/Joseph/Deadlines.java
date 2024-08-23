package Joseph;

public class Deadlines extends ToDo {
    private String due;

    public Deadlines(String desc, String due) {
        super(desc);
        this.due = due;
    }

    public String getDue() {
        return this.due;
    }
}
