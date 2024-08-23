public class UnknownCommandException extends Exception {
    public UnknownCommandException(Command command) {
        super("Sumo dunno your command \"" + command +"\" ! Check spelling of your first word.");
    }
}
