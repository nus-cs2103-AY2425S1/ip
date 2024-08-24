public class HamyoException extends Exception{
  public HamyoException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Oh No! " + this.getMessage();
  }
}
