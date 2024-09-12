package util;

import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.EventException;
import exceptions.FindException;
import exceptions.ToDoException;
import exceptions.UpdateMarkedException;

/**
 * Utility class containing validation functions.
 */
public class Validator {
    /**
     * Method to validate the todo command.
     *
     * @param details The full split input from the user.
     * @throws ToDoException if the input is malformed.
     */
    public static void verifyTodo(String[] details) throws ToDoException {
        assert details != null : "Details must not be null";

        if (details.length <= 1) {
            throw new ToDoException("Missing a description for this todo!");
        }
    }

    /**
     * Method to validate the deadline command.
     *
     * @param details The full split input from the user.
     * @param byIdx The idx of "/by" in details.
     * @throws DeadlineException if the input is malformed.
     */
    public static void verifyDeadline(String[] details, int byIdx) throws DeadlineException {
        assert details != null : "Details must not be null";

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
     * @param toIdx The idx of "/to" in details.
     * @throws EventException if the input is malformed.
     */
    public static void verifyEvent(String[] details, int fromIdx, int toIdx) throws EventException {
        assert details != null : "Details must not be null";

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
        assert details != null : "Details must not be null";

        if (details.length <= 1) {
            throw new UpdateMarkedException("Too few arguments missing idx to mark/ unmark",
                    details[0]);
        }
        try {
            Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new UpdateMarkedException(
                    String.format("Last I checked (%s)'s no int :/", details[1]), details[0]);
        }
    }

    /**
     * Method to validate the delete command.
     *
     * @param details The split input from the user.
     * @throws DeleteException if missing details.
     */
    public static void verifyDelete(String[] details) throws DeleteException {
        assert details != null : "Details must not be null";

        if (details.length <= 1) {
            throw new DeleteException("Too few arguments! Missing idx.");
        }

        for (int i = 1; i < details.length; i++) {
            try {
                Integer.parseInt(details[i]);
            } catch (NumberFormatException e) {
                throw new DeleteException(
                        String.format("Last I checked (%s)'s no int :/", details[i]));
            }
        }
    }

    /**
     * Method to validate the find command.
     *
     * @param details The split input from the user.
     * @throws FindException if missing details.
     */
    public static void verifyFind(String[] details) throws FindException {
        assert details != null : "Details must not be null";

        if (details.length <= 1) {
            throw new FindException("Incomplete request! Too few argument, missing: keyword");
        }
    }
}
