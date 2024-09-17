package james;

public class MissingDeadlineException extends JamesException{
    public MissingDeadlineException() {
        super("Oops! looks like you missed out the deadline! please add a \"/by\" followed by the deadline after" +
                " the task description");
    }
}
