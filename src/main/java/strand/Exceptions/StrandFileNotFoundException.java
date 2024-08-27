package strand.Exceptions;

public class StrandFileNotFoundException extends StrandException {
    @Override
    public String toString() {
        return "File not found " + super.toString();
    }
}
