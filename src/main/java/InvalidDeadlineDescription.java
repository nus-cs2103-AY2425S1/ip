public class InvalidDeadlineDescription extends Exception {
    public InvalidDeadlineDescription() {
        super("Sorry traveller. For deadlines, you need to have both the description and the deadline separated by '/'");
    }
}
