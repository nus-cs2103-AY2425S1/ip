public class DeleteTaskInputException extends Exception {
    public DeleteTaskInputException() {
        super("Delete task input must be in the form `delete [integer], where integer is the task number in"
                + " the tasks list");
    }
}
