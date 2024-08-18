public class InvalidCommandException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "I couldn't understand that.. Try giving me a valid command!";
    }

}
