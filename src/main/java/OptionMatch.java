public class OptionMatch {
  private final int index;
  private final String match;

  public OptionMatch(int index, String match) {
    this.index = index;
    this.match = match;
  }

  public int getIndex() {
    return this.index;
  }

  public String getMatch() {
    return this.match;
  }
}
