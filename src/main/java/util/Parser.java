package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import MizzExceptions.DeadlineException;
import MizzExceptions.EventException;
import MizzExceptions.MizzException;
import MizzExceptions.ToDoException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Parser {

  public static List<Task> parseFromStorage(Storage storage) throws MizzException {
    List<Task> result = new ArrayList<>();

    String[] entries = storage.toArray();

    for (String entry : entries) {
      if (entry.length() < 7) {
        throw new MizzException("Incomplete entry!:" + entry);
      }
      String[] parts = entry.substring(7).split("\\s+");
      char taskType = entry.charAt(1);
      String details = String.join(" ", parts);
      if (details.isEmpty()) {
        throw new MizzException("Missing details: " + entry);
      }
      Task t;
      switch (taskType) {
        case 'T': {
          t = new ToDo(details);
          result.add(t);
          break;
        }
        case 'D': {
          int byIdx = details.indexOf(" (by: ");
          String description = details.substring(0, byIdx);
          System.out.println(details.length());
          String by = details.substring(byIdx + 6, details.length() - 1);
          try {
            LocalDate byDate = LocalDate.parse(by);
            t = new Deadline(description, byDate);
            result.add(t);
          } catch (DateTimeParseException e) {
            throw new MizzException("Invalid date time format: " + e.getMessage());
          }
          break;
        }
        case 'E': {
          int fromIdx = details.indexOf(" (from: ");
          int toIdx = details.indexOf(" to: ");
          String description = details.substring(0, fromIdx);
          String from = details.substring(fromIdx + 8, toIdx);
          String to = details.substring(toIdx + 5, details.length() - 1);
          try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            t = new Event(description, fromDate, toDate);
            result.add(t);
          } catch (DateTimeParseException e) {
            throw new MizzException("Invalid date time format: " + e.getMessage());
          }
          break;
        }
        default:
          throw new MizzException("Invalid entry found in file!: " + taskType);
      }
      if (entry.charAt(4) == 'X') {
        t.markDone();
      }
    }

    return result;
  }

  /**
   * Utility method to parse and clean the user input.
   * 
   * @param inputString The input from the scanner.
   * @return The parsed string as an array of size 4 where:
   *         <ul>
   *         <li>parsedString[0] -> command</li>
   *         <li>parsedString[1] -> description</li>
   *         <li>parsedString[2] -> "" if todo | by if deadline | from if
   *         event</li>
   *         <li>parsedString[3] -> to if event else ""</li>
   *         </ul>
   */
  public static String[] parseStringInput(String inpuString) throws MizzException {
    String[] result = new String[4];
    String[] parts = inpuString.split("\\s+");
    result[0] = parts[0].toLowerCase();

    if (result[0].equals("mark") || result[0].equals("unmark")) {
      Validator.verifyMarkUnmark(parts);
      result[1] = parts[1];
      return result;
    }

    if (result[0].equals("delete")) {
      Validator.verifyDelete(parts);
      result[1] = parts[1];
      return result;
    }

    if (result[0].equals("list") || result[0].equals("bye")) {
      return result;
    }

    switch (result[0]) {
      case "todo":
        Parser.parseTodo(parts, result);
        return result;
      case "deadline":
        Parser.parseDeadline(parts, result);
        return result;
      case "event":
        Parser.parseEvent(parts, result);
        return result;
      default:
        break;
    }

    return result;
  }

  private static String[] parseTodo(String[] parts, String[] result) throws ToDoException {
    Validator.verifyTodo(parts);
    result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
    return result;
  }

  private static String[] parseDeadline(String[] parts, String[] result) throws DeadlineException {
    int byIdx = -1;
    for (int i = 1; i < parts.length; i++) {
      if (byIdx == -1 && parts[i].equals("/by")) {
        byIdx = i;
        break;
      }
    }

    Validator.verifyDeadline(parts, byIdx);
    result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, byIdx));
    result[2] = String.join(" ", Arrays.copyOfRange(parts, byIdx + 1,
        parts.length));
    return result;
  }

  private static String[] parseEvent(String[] parts, String[] result) throws EventException {
    int fromIdx = -1;
    int toIdx = -1;

    for (int i = 1; i < parts.length; i++) {
      if (fromIdx == -1 && parts[i].equals("/from")) {
        fromIdx = i;
      } else if (toIdx == -1 && parts[i].equals("/to")) {
        toIdx = i;
      }
    }
    Validator.verifyEvent(parts, fromIdx, toIdx);
    result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, fromIdx));
    result[2] = String.join(" ", Arrays.copyOfRange(parts, fromIdx + 1, toIdx));
    result[3] = String.join(" ", Arrays.copyOfRange(parts, toIdx + 1, parts.length));
    return result;
  }
}
