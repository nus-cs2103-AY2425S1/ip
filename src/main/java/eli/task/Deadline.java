package eli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Deadline task with a specific deadline.
 */
public class Deadline extends Task {
  private LocalDateTime deadline;

  /**
   * Constructor for Deadline.
   *
   * @param task The description of the ToDo task.
   * @param deadline The deadline of the ToDo task.
   */
  public Deadline(String task, LocalDateTime deadline) {
    super(task);
    this.deadline = deadline;
  }

  /**
   * Constructor for Deadline.
   *
   * @param task The description of the ToDo task.
   * @param deadline The deadline of the ToDo task.
   */
  public Deadline(String task, String deadline) {
    super(task);
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
      this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid date format for deadline: " + deadline);
    }
  }

  @Override
  public String toFileFormat() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    String formattedDeadline = deadline.format(formatter);
    return "D | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask() + " | " + formattedDeadline;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    String formattedDeadline = deadline.format(formatter);
    return "[D] " + super.toString() + " (by: " + formattedDeadline + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deadline that = (Deadline) o;
    return Objects.equals(this.getTask(), that.getTask()) &&
            Objects.equals(deadline, that.deadline) && this.getStatus() == that.getStatus();}

}