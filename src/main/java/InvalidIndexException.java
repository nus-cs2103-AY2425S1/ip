public class InvalidIndexException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "This task index doesn't seem right to me.. Let's fix it, shall we?\n"
                + "** Pro-tip: pass me an integer in the range of existing task indices ;)";
    }

}
