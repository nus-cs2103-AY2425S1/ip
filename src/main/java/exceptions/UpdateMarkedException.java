package exceptions;

import util.Utility;

/**
 * Class for a mark | unmark exception.
 */
public class UpdateMarkedException extends MizzException {
    private final boolean mark;

    /**
     * Custom constructor based on wheter its mark or unmark.
     *
     * @param msg The error msg.
     * @param mark The command can be mark or marked.
     */
    public UpdateMarkedException(String msg, String mark) {
        super(msg);
        this.mark = mark.equals("mark");
    }

    @Override
    public String toString() {
        return Utility.INDENT + "Thats bad marking >:( " + super.getMessage() + Utility.NEW_LINE
                + (this.mark ? Utility.INDENT + "Example usage: mark <valid_idx_from_1>"
                        : Utility.INDENT + "Example usage: unmark <valid_idx_from_1>");
    }
}
