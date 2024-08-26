public enum TaskType {
  TODO,
  DEADLINE,
  EVENT;

  public char toChar() {
    switch (this) {
      case TODO:
        return 'T';
      case DEADLINE:
        return 'D';
      case EVENT:
        return 'E';
      default:
        throw new UnhandledTaskTypeException(this);
    }
  }

  public static TaskType fromChar(char taskType) {
    switch (taskType) {
      case 'T':
        return TODO;
      case 'D':
        return DEADLINE;
      case 'E':
        return EVENT;
      default:
        throw new UnhandledTaskTypeException(taskType);
    }
  }
}