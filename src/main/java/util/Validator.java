package util;

import MizzExceptions.*;

public class Validator {
  /**
   * Method to validate the todo command.
   * 
   * @param details The full split input from the user.
   * @throws ToDoException if the input is malformed.
   */
  public static void verifyTodo(String[] details) throws ToDoException {
    if (details.length == 1) {
      throw new ToDoException("Missing a descrption for this todo!");
    }
  }

  /**
   * Method to validate the deadline command.
   * 
   * @param details The full split input from the user.
   * @param byIdx   The idx of "/by" in details.
   * @throws DeadlineException if the input is malformed.
   */
  public static void verifyDeadline(String[] details, int byIdx) throws DeadlineException {
    int maxAllowableIdx = details.length - 1;
    if (byIdx == -1) {
      throw new DeadlineException("Missing /by in the input!");
    }
    if (byIdx == maxAllowableIdx) {
      throw new DeadlineException("Missing a string behind /by... by when?");
    }
    if (byIdx == 1) {
      throw new DeadlineException(
          "Hmmm I'm not sure whats the deadline for? Missing description!");
    }
  }

  /**
   * Method to validate the event command.
   * 
   * @param details The full split input from the user.
   * @param fromIdx The idx of "/from" in details.
   * @param toIdx   The idx of "/to" in details.
   * @throws EventException if the input is malformed.
   */
  public static void verifyEvent(String[] details, int fromIdx, int toIdx) throws EventException {
    int maxAllowableIdx = details.length - 1;
    if (fromIdx == 1) {
      throw new EventException("Hmmm what's this event about? Missing description!");
    }
    if (fromIdx == -1) {
      throw new EventException("Missing /from in the input!");
    }
    if (toIdx == -1) {
      throw new EventException("To infinity! Missing /to in the input!");
    }
    if (fromIdx > toIdx) {
      throw new EventException("/from should come before /to!");
    }
    if (fromIdx + 1 >= toIdx) {
      throw new EventException("Missing a string behind /from... from when?");
    }
    if (toIdx == maxAllowableIdx) {
      throw new EventException("Hm when does this event end? Missing info behind /to");
    }
  }

  /**
   * Method to validate the mark or unmark command.
   * 
   * @param details The full split input from the user.
   * @throws UpdateMarkedException if the input is malformed.
   */
  public static void verifyMarkUnmark(String[] details) throws UpdateMarkedException {
    if (details.length == 1) {
      throw new UpdateMarkedException("Too few arguments missing idx to mark/ unmark");
    }
    try {
      Integer.parseInt(details[1]);
    } catch (NumberFormatException e) {
      throw new UpdateMarkedException(
          String.format("Last I checked (%s)'s no int :/", details[1]));
    }
  }
}
