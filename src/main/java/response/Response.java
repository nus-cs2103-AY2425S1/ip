package response;

import java.util.List;

public class Response {
  private final List<String> response;
  private final boolean exit;

  public Response(List<String> response, boolean exit) {
    this.response = response;
    this.exit = exit;
  }

  public Response(List<String> response) {
    this.response = response;
    this.exit = false;
  }

  public List<String> getResponse() {
    return this.response;
  }

  public boolean shouldExit() {
    return this.exit;
  }
}

