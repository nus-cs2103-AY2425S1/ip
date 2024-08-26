package vuewee.task;

class UnhandledTaskTypeException extends RuntimeException {
  public UnhandledTaskTypeException(char enumValue) {
    super("Unexpected TaskType char value: " + enumValue);
  }

  public UnhandledTaskTypeException(TaskType enumValue) {
    super("Unhandled TaskType enum value: " + enumValue);
  }
}
