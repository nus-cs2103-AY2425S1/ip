package moimoi.exception;

/**
 * Represents an exception related to invalid index input.
 */
public class InvalidIndexException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "This task index doesn't seem right to me.. Let's fix it, shall we?\n"
                + "** Pro-tip: pass me an integer in the range of existing task indices ;)";
    }

}
