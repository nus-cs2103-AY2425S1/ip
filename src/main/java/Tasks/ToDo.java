package Tasks;

/**
 * Represents a Task with only a description.
 */
public class ToDo  extends Task{

    /**
     * Takes in a string as the task description.
     *
     * @param s Task description.
     */
    public ToDo(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String infoForFile() {
        String str =  "[T] / " + super.getDetails();
        return str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String str = "";
        str = str + "[T]";
        str = str + super.toString();
        return str;
    }
}
