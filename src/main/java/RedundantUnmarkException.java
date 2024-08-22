public class RedundantUnmarkException extends Exception {
  public RedundantUnmarkException(Task task) {
    super("This task is already marked as not done:\n  " + task.toString());
  }
}
