package rotodo.exception;

/**
 * The InvalidInputException class encapsulates
 * the exception for invalid user inputs.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class InvalidInputException extends Exception {
    private static final int ERROR_TYPE_ONE = 3;
    private static final int ERROR_TYPE_TWO = 8;
    private static int count = 0;
    private static String[] error = new String[] {
        "RoTodo.Error: Invalid Input",
        "Ro7o0OoODdDDdDDDoO0.o.oo >< .. . 3RR0rrre3rrr4rrrrr"
                + "rrrrrrrrrrrr.rrr.r..r..r...r......r.r..........",
        "01010010011011110101010001101111011001000 oVeRH3471n9"
                + " 7o0 m4nY 3rR0r 10011110010111001000101011100100111"
                + "00100110111101110010"};

    /**
     * Initialise the InvalidInputException.
     *
     * @param message the detail message.
     */
    public InvalidInputException(String message) {
        super("\u001B[31m" + (count < ERROR_TYPE_ONE ? error[0]
                : (count < ERROR_TYPE_TWO ? error[1] : error[2]))
                + "\n  " + message + "\u001B[0m\n"
                + "type 'help' to see guide");
        count += 1;
    }

    public static void noError() {
        count -= 1;
    }

    @Override
    public String toString() {
        return getLocalizedMessage();
    }
}
