import processor.Echo;
import processor.Exit;
import response.Response;

import java.util.function.Function;

public class MessageParser {
  public static Function<String, Response> parse(String prompt) {

    switch (prompt) {
      case "bye":
        return Exit::process;
      default:
        return Echo::process;
    }

  }
}
