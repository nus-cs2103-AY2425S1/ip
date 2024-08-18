public class Event extends Task {
  private String from;
  private String to;

  public Event(String description) throws InfinityException {
    try {
      this.setDescription(description.split(" /from ")[0]);
      this.from = description.split(" /from ")[1].split(" /to ")[0];
      this.to = description.split(" /to ")[1];
      this.setTypeOfTask("E");
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new InfinityException("oops, I think your format is a little wrong");
    }
  }

  @Override
  public String toString() {
    return String.format("[%s][%s] %s (from: %s, to: %s)", this.typeOfTask, this.isDone ? "X" : " ", this.description, this.from, this.to);
  }
}