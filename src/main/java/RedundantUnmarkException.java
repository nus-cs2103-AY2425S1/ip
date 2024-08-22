public class RedundantUnmarkException extends TaskListException {
  public RedundantUnmarkException(Task task) {
    super("This task is already marked as not done:\n  " + task.toString());
  }
}
