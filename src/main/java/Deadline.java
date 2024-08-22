public class Deadline extends Task {
    private String due;

    public Deadline(String... parems) throws IllegalArgumentException {
        super(parems[0].strip());
        if (parems.length < 2) {
            throw new IllegalArgumentException("Deadline: You did not provide a due date/time.");
        }

        StringBuilder str = new StringBuilder(parems[1].strip());
        str.insert(str.indexOf(" "), ':');
        this.due = str.toString();
    }

    @Override
    public String toString() {
        String mark = isDone() ? "X" : " ";
        return String.format("[D][%s] %s (%s)", mark, getDescription(), due);
    }
}
