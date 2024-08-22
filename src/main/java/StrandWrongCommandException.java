public class StrandWrongCommandException extends StrandException{
    @Override
    public String toString() {
        return "Command not found " + super.toString();
    }
}
