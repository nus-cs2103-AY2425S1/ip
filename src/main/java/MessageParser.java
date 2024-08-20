import processor.Echo;
import processor.Exit;
import processor.text.Add;
import response.Response;

import java.util.function.Function;

public class MessageParser {
  public static Function<String, Response> parse(String prompt) {

    switch (prompt) {
      case "list":
        return processor.text.List::process;
      case "bye":
        return Exit::process;
      default:
        return Add::process;
    }

  }
}
