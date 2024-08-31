package rainy.database;
import rainy.tasks.*;
import rainy.rainyexceptions.*;

/**
 * Represents the set of user commands issued by the user.
 */

public enum Instructions {
    /**
     * <h1>Instructions created through user input</h1>
     * An <code>Instructions</code> object
     * is created through the following user commands: bye, list, mark, unmark, delete, todo, deadline,
     * event, sort, and invalid if it is anything else.
     */
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    SORT,
    INVALID
}