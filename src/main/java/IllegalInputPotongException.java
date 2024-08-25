public class IllegalInputPotongException extends PotongException {

    public IllegalInputPotongException() {
        super("OOPS!! Your input is wrong :-(");
    }

    @Override
    public String toString() {
        return "OOPS!! Your input is wrong :-(";
    }
}
