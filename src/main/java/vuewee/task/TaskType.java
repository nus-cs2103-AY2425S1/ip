package vuewee.task;

public enum TaskType {
  TODO('T'),
  DEADLINE('D'),
  EVENT('E');

  private final char taskTypeChar;

  TaskType(char taskType) {
    this.taskTypeChar = taskType;
  }

  public char toChar() {
    return taskTypeChar;
  }

  public static TaskType fromChar(char taskType) {
    for (TaskType type : TaskType.values()) {
      if (type.taskTypeChar == taskType) {
        return type;
      }
    }
    throw new UnhandledTaskTypeException(taskType);
  }
}
