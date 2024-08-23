public class IllegalMarkException extends IllegalCommandException {

  public IllegalMarkException() {
    super();
  }

  public IllegalMarkException(String message) {
    super(message);
  }

  public IllegalMarkException(Task task, boolean isDone) {
    super(
        (isDone ? "This task is already done:\n  " : "This task is already marked as not done:\n  ") + task.toString());
    ;
  }
}
