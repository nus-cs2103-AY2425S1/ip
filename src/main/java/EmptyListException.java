public class EmptyListException extends Exception {
  public EmptyListException() {
    super("You have no tasks in your list.");
  }
}
