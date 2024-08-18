package command;

public abstract class Command {
  private String message;

  public Command(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    String dashes = "-".repeat(100);
    return dashes + "\n"
      + this.message + "\n"
      + dashes;
  }

  public abstract void execute();
}
