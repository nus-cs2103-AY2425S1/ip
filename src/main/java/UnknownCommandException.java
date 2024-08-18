public class UnknownCommandException extends Exception {
    public UnknownCommandException(String command) {
        super("SUMO dunno your command \"" + command +"\" ! Check spelling of your first word.");
    }
}
