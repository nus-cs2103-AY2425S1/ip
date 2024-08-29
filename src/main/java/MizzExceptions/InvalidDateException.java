package MizzExceptions;

import util.Utility;

public class InvalidDateException extends MizzException {
    public InvalidDateException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return Utility.INDENT + "Invalid date format!: " + super.getMessage() + Utility.NEW_LINE
                + Utility.INDENT + "Example format: 2024-01-02 (for 2nd january 2024)";
    }
}
