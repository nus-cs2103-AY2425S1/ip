package easton;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getCsvFormat() {
        return "T,"+ super.getCsvFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
