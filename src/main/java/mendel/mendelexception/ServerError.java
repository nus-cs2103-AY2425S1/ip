package mendel.mendelexception;

public class ServerError extends RuntimeException {
  public ServerError(String problem) {
    super(String.format("Internal Server Error: %s\nApplication will be closed", problem));
  }
  @Override
  public String toString() {
    return super.getMessage();
  }
}
