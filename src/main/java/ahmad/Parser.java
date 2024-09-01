package ahmad;

import ahmad.exceptions.CommandInvalidException;
import ahmad.processor.Exit;
import ahmad.processor.Processor;
import ahmad.processor.task.*;

import java.util.Arrays;
import java.util.List;

public class Parser {
  public static Processor parse(String prompt) throws CommandInvalidException {

    final List<String> prompts = Arrays.asList(prompt.split(" "));

    switch (prompts.get(0)) {
      case "mark":
        return Mark::process;
      case "unmark":
        return Unmark::process;
      case "list":
        return ahmad.processor.task.List::process;
      case "bye":
        return Exit::process;
      case "todo":
        return Add::todo;
      case "deadline":
        return Add::deadline;
      case "event":
        return Add::event;
      case "delete":
        return Delete::process;
      case "find":
        return Find::process;
    }
    throw new CommandInvalidException();
  }
}
