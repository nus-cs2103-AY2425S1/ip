package exception;

public class CitadelTaskNoInput extends CitadelException {
    @Override
    public String toString() {
        return super.toString() + "The description of the item cannot be empty.";
     }
}
