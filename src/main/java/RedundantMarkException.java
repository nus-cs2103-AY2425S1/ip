public class RedundantMarkException extends TaskListException {
  public RedundantMarkException(Task task) {
    super("This task is already done:\n  " + task.toString());
  }
}
