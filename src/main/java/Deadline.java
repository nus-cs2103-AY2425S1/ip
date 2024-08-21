public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        StringBuilder str = new StringBuilder(due.strip());
        str.insert(str.indexOf(" "), ':');
        this.due = str.toString();
    }

    @Override
    public String toString() {
        String mark = isDone() ? "X" : " ";
        return String.format("[D][%s] %s (%s)", mark, getDescription(), due);
    }
}
