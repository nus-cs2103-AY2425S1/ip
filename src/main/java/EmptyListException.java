public class EmptyListException extends TaskListException {
  public EmptyListException() {
    super("You have no tasks in your list.");
  }
}
