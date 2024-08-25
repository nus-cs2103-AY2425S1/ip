package moimoi.exception;

/**
 * Represents an exception related to missing argument input.
 */
public class MissingArgumentException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "I need more information to help you out! Pass me some more arguments~\n"
                + "** Pro-tip: double-check your slash command(s) ;)";
    }

}
