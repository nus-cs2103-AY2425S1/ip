public class Deadline extends Task {
  private String description;
  private String time;

  public Deadline(String line) {
    super(line);
    int slashIndex = line.indexOf("/");
    this.description = line.substring(slashIndex - 1);
    this.time = line.substring(slashIndex + 4);
  }

  @Override
  public String toString() {
    return String.format("[D][%s] %s (by: %s)", super.completedStringRepresentation(), description, time);
  }
}
