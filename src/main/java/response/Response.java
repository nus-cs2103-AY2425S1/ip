package response;

import java.util.List;

public class Response {
  private final List<String> response;
  private final boolean exit;
  private final boolean shouldRecord;

  public Response(List<String> response, boolean exit, boolean shouldRecord) {
    this.response = response;
    this.exit = exit;
    this.shouldRecord = shouldRecord;
  }

  public Response(List<String> response, boolean exit) {
    this.response = response;
    this.exit = exit;
    this.shouldRecord = false;
  }

  public Response(List<String> response) {
    this.response = response;
    this.exit = false;
    this.shouldRecord = false;
  }

  public List<String> getResponse() {
    return this.response;
  }

  public boolean shouldExit() {
    return this.exit;
  }

  public boolean checkShouldRecord() {
    return this.shouldRecord;
  }
}

