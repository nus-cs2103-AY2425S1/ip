public class UnhandledTaskTypeException extends RuntimeException {
  public UnhandledTaskTypeException(TaskType enumValue) {
    super("Unhandled enum value: " + enumValue);
  }
}
