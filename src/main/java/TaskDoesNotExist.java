public class TaskDoesNotExist extends Exception {
    public TaskDoesNotExist() {
        super("The task you are trying to access does not exist");
    }
}
