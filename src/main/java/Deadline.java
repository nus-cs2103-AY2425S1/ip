public class Deadline extends Task {
  private String description;
  private String time;

  public Deadline(String line) throws LukeException {
    super(line);
    int slashIndex = line.indexOf(" /by ");
    if (slashIndex == -1) {
      if (line.contains("/by")) {
        throw new LukeException("There needs to be spacing between /by and other words.");
      } else {
        throw new LukeException("Missing /by to indicate when the deadline of the task.");
      }
    } else {
      this.description = line.substring(0, slashIndex).trim();
      this.time = line.substring(slashIndex + 4).trim();
    }
  }

  @Override
  public String toString() {
    return String.format("[D][%s] %s (by: %s)", super.completedStringRepresentation(), description, time);
  }
}
