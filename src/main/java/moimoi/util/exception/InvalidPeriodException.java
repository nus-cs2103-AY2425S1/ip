package moimoi.util.exception;

/**
 * Represents an exception related to invalid period input.
 */
public class InvalidPeriodException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "This period seems suspicious.. Fix IS needed!\n"
                + "** Pro-tip: pass me a positive integer or decimal, representing the period in hours ;)";
    }

}
