public class UnknownCmdException extends MendelException {
    public UnknownCmdException() {
        super("OOPS! Unknown command. ");
    }
    @Override
    public String toString() {
        return super.toString() + "Please check that the first word is a known command";
    }
}
