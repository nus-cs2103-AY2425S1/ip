import processor.Echo;
import processor.Exit;
import processor.task.Add;
import processor.task.Mark;
import processor.task.Unmark;
import response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MessageParser {
  public static Function<String, Response> parse(String prompt) {

    final List<String> prompts = Arrays.asList(prompt.split(" "));

    if (prompts.size() == 2) {
      switch (prompts.get(0)) {
        case "mark":
          return Mark::process;
        case "unmark":
          return Unmark::process;
      }
    }

    switch (prompt) {
      case "list":
        return processor.task.List::process;
      case "bye":
        return Exit::process;
      default:
        return Add::process;
    }

  }
}
