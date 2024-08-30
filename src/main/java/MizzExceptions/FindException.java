package MizzExceptions;

import util.Utility;

public class FindException extends MizzException {
    public FindException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return Utility.INDENT + "Invalid find command: " + super.getMessage() + Utility.NEW_LINE
                + Utility.INDENT + "Example usage: find <keyword>";
    }
}
