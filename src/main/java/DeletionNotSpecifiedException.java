public class DeletionNotSpecifiedException extends StelleException{
    public DeletionNotSpecifiedException() {
        super("Deletion must specify a task!");
    }
}
