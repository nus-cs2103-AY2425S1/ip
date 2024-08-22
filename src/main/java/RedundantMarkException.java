public class RedundantMarkException extends Exception {
  public RedundantMarkException(Task task) {
    super("This task is already done:\n  " + task.toString());
  }
}
