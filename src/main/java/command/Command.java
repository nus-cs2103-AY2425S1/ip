package command;

public abstract class Command {
  private final String message;

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
