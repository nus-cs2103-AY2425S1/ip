import java.io.Serializable;

public class ToDo extends Task implements Serializable {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
