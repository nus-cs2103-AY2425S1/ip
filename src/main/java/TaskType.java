public enum TaskType {
  TODO,
  DEADLINE,
  EVENT;

  public static char toChar(TaskType taskType) {
    switch (taskType) {
      case TODO:
        return 'T';
      case DEADLINE:
        return 'D';
      case EVENT:
        return 'E';
      default:
        throw new UnhandledTaskTypeException(taskType);
    }
  }
}