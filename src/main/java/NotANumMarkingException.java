public class NotANumMarkingException extends RuntimeException {

    public NotANumMarkingException(int taskListNumber) {
        super(taskListNumber + " is not a numbered Task in the TaskList!");
    }
}
