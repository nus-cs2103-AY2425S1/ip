package moimoi.exception;

/**
 * Represents an exception related to invalid date-time/date input.
 */
public class InvalidDateTimeException extends MoiMoiException {

    String type;
    String formatter;

    /**
     * Constructs an exception for the specified type of invalid input.
     *
     * @param type Type of invalid input (i.e., "date-time"/"date").
     */
    public InvalidDateTimeException(String type) {
        this.type = type;
        if (this.type.equals("date-time")) {
            this.formatter = "yyyy-MM-dd HH:mm";
        } else if (this.type.equals("date")) {
            this.formatter = "yyyy-MM-dd";
        }
    }

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "The " + this.type
                + " format doesn't make sense to me.. This calls for a fix!\n"
                + "** Pro-tip: pass me valid " + this.type + "s in the format '" + this.formatter + "' ;)";
    }

}
