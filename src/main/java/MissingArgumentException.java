public class MissingArgumentException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "I need more information to help you out! Pass me some more arguments~\n"
                + "** Pro-tip: double-check your slash command(s) ;)";
    }

}
