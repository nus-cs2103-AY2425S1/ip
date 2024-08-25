package moimoi.exception;

/**
 * Represents an exception related to invalid date-time range.
 */
public class InvalidDateTimeRangeException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "Need some fixing on the date range here!\n"
                + "** Pro-tip: the starting time shouldn't be later than the ending time ;)";
    }

}
