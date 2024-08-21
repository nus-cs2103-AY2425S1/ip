import exceptions.CommandInvalidException;
import processor.Exit;
import processor.Processor;
import processor.task.Add;
import processor.task.Mark;
import processor.task.Unmark;

import java.util.Arrays;
import java.util.List;

public class MessageParser {
  public static Processor parse(String prompt) throws CommandInvalidException {

    final List<String> prompts = Arrays.asList(prompt.split(" "));

    switch (prompts.get(0)) {
      case "mark":
        return Mark::process;
      case "unmark":
        return Unmark::process;
      case "list":
        return processor.task.List::process;
      case "bye":
        return Exit::process;
      case "todo":
        return Add::todo;
      case "deadline":
        return Add::deadline;
      case "event":
        return Add::event;
    }
    throw new CommandInvalidException();
  }
}
