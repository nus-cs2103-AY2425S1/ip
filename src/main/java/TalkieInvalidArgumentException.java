public class TalkieInvalidArgumentException extends TalkieException {

    protected String command;
    protected String hint;

    public TalkieInvalidArgumentException(String command, String hint) {
       this.command = command;
       this.hint = hint;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------------\n"
                + super.toString() + " Your " + this.command + " has invalid arguments!\n"
                + "Hint: " + hint +"\n"
                + "Please try again! :D\n"
                + "------------------------------------------------------------\n";
    }
}
