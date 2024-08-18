public class UnknownCommandException extends Exception {
    public UnknownCommandException(String command) {
        super("Sumo dunno your command \"" + command +"\" ! Check spelling of your first word.");
    }
}
