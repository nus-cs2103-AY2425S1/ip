package elon;

import elon.command.AddTaskCommand;
import elon.command.Command;
import elon.command.DeleteTaskCommand;
import elon.command.ExitCommand;
import elon.command.FindTaskCommand;
import elon.command.ListTaskCommand;
import elon.command.MarkTaskCommand;
import elon.command.SnoozeTaskCommand;
import elon.command.UnmarkTaskCommand;
import elon.task.Deadline;
import elon.task.Event;
import elon.task.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/** Parses user input commands and interacts with the UI, TaskList, and Storage. */
public class Parser {
  public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  /**
   * Parses the input command array and returns the corresponding Command object.
   *
   * @param inputArr the array of user input strings
   * @return a Command object corresponding to the user input
   * @throws ElonException if the input command is invalid or required parameters are missing
   */
  public static Command parse(String[] inputArr) throws ElonException {
    assert inputArr.length > 0 : "Input command array is empty";
    String commandPhrase = inputArr[0];
    switch (commandPhrase) {
      case "list":
        return new ListTaskCommand();
      case "mark":
        int indexToMark = Integer.parseInt(inputArr[1]) - 1;
        return new MarkTaskCommand(indexToMark);
      case "unmark":
        int indexToUnmark = Integer.parseInt(inputArr[1]) - 1;
        return new UnmarkTaskCommand(indexToUnmark);
      case "delete":
        int indexToDelete = Integer.parseInt(inputArr[1]) - 1;
        return new DeleteTaskCommand(indexToDelete);
      case "bye":
        return new ExitCommand();
      case "find":
        String searchWord = String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
        return new FindTaskCommand(searchWord);
      case "todo":
        if (inputArr.length <= 1) {
          throw new ElonException("Error. Description for ToDo task not specified.");
        }
        String todoTask = "";
        for (int i = 1; i < inputArr.length; i++) {
          todoTask += inputArr[i] + " ";
        }
        todoTask = todoTask.strip();
        ToDo todo = new ToDo(todoTask, false);
        return new AddTaskCommand(todo);
      case "deadline":
        if (inputArr.length <= 1) {
          throw new ElonException(
              "Error. Description and By date for Deadline task not specified.");
        }
        int j = 1;
        String deadlineTask = "";
        while (!inputArr[j].equals("/by")) {
          deadlineTask += inputArr[j] + " ";
          j++;
        }
        deadlineTask = deadlineTask.strip();
        String by = "";
        if (inputArr.length <= j + 1) {
          throw new ElonException("Error. By date for Deadline task not specified.");
        }
        for (int k = j + 1; k < inputArr.length; k++) {
          by += inputArr[k] + " ";
        }
        by = by.strip();
        try {
          LocalDateTime byDate = LocalDateTime.parse(by, Parser.formatter);
          Deadline deadline = new Deadline(deadlineTask, false, byDate);
          return new AddTaskCommand(deadline);
        } catch (DateTimeParseException e) {
          throw new ElonException(
              "Wrong date format. Follow yyyy-MM-dd HH:mm (year-month-day hours:minutes");
        }
      case "event":
        if (inputArr.length <= 1) {
          throw new ElonException(
              "Error. Description, From and To date for Event task not specified.");
        }
        int x = 1;
        String eventTask = "";
        while (!inputArr[x].equals("/from")) {
          eventTask += inputArr[x] + " ";
          x++;
        }
        eventTask = eventTask.strip();
        x++;
        if (inputArr.length <= x) {
          throw new ElonException("Error. From date for Event task not specified.");
        }
        String from = "";
        while (!inputArr[x].equals("/to")) {
          from += inputArr[x] + " ";
          x++;
        }
        from = from.strip();
        x++;
        if (inputArr.length <= x) {
          throw new ElonException("Error. To date for Event task not specified.");
        }
        String to = "";
        for (int y = x; y < inputArr.length; y++) {
          to += inputArr[y] + " ";
        }
        to = to.strip();
        try {
          LocalDateTime fromDate = LocalDateTime.parse(from, Parser.formatter);
          LocalDateTime toDate = LocalDateTime.parse(to, Parser.formatter);
          Event event = new Event(eventTask, false, fromDate, toDate);
          return new AddTaskCommand(event);
        } catch (DateTimeParseException e) {
          throw new ElonException(
              "Wrong date format. Follow yyyy-MM-dd HH:mm (year-month-day hours:minutes");
        }
      case "snooze":
        if (inputArr.length <= 1) {
          throw new ElonException("Index of task to snooze not specified");
        }
        if (inputArr.length <= 2) {
          throw new ElonException("New date to postone task to not specified");
        }
        int taskIndex = Integer.parseInt(inputArr[1]) - 1;
        String date = "";
        for (int z = 2; z < inputArr.length; z++) {
          date += inputArr[z] + " ";
        }
        date = date.trim();
        try {
          LocalDateTime newDate = LocalDateTime.parse(date, Parser.formatter);
          return new SnoozeTaskCommand(taskIndex, newDate);
        } catch (DateTimeParseException e) {
          throw new ElonException(
              "Wrong date format. Follow yyyy-MM-dd HH:mm (year-month-day hours:minutes.");
        }
      default:
        throw new ElonException("Invalid Command: " + commandPhrase);
    }
  }
}
