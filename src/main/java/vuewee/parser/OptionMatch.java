package vuewee.parser;

public class OptionMatch<T> {
  private final int index;
  private final T match;

  public OptionMatch(int index, T match) {
    this.index = index;
    this.match = match;
  }

  public int getIndex() {
    return this.index;
  }

  public T getMatch() {
    return this.match;
  }
}
