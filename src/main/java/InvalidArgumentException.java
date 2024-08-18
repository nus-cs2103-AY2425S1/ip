public class InvalidArgumentException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "This task index doesn't seem right to me.. Let's fix it, shall we?";
    }

}
