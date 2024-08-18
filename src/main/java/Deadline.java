public class Deadline extends Task {
  private String by;

  public Deadline(String description) throws InfinityException {
    try {
      this.setDescription(description.split(" /by ")[0]);
      this.by = description.split(" /by ")[1];
      this.setTypeOfTask("D");
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new InfinityException("oops, I think your format is a little wrong");
    }
  }

  @Override
  public String toString() {
    return String.format("[%s][%s] %s (by: %s)", this.typeOfTask, this.isDone ? "X" : " ", this.description, this.by);
  }
}