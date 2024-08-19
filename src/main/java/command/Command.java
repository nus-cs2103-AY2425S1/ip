package command;

import java.util.function.Supplier;

public abstract class Command {
  private final Supplier<String> messageSupplier;

  public Command(Supplier<String> messageSupplier) {
    this.messageSupplier = messageSupplier;
  }

  @Override
  public String toString() {
    String dashes = "-".repeat(100);
    return dashes + "\n"
      + this.messageSupplier.get() + "\n"
      + dashes;
  }

  public abstract void execute();
}
