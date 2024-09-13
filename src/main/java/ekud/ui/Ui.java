package ekud.ui;

/**
 * An abstract class that represents the UI of EKuD,
 * responsible for outputting EKuD's responses and reading the user's input.
 *
 * @author uniqly
 * @see ekud.Ekud
 */
public abstract class Ui {
    protected static final String GREETING_MESSAGE =
            "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
    protected static final String GOODBYE_MESSAGE =
            "I hope you enjoyed your stay!\nSee you next time! NOT!!";

    /** Buffer of Strings to print as one output */
    private final StringBuilder bufferedOutputs = new StringBuilder();

    /**
     * Clears the buffer.
     */
    public void clearBuffer() {
        bufferedOutputs.setLength(0);
    }

    /**
     * Adds input to output buffer.
     *
     * @param input The input to add to the buffer.
     */
    public void addToBuffer(String input) {
        if (!bufferedOutputs.isEmpty()) { // add newline if more than one line in buffer
            bufferedOutputs.append("\n");
        }
        bufferedOutputs.append(input);
    }

    /**
     * Formats inputs following a format and adds it to the buffer.
     * @param format The {@link String} format to follow.
     * @param inputs The list of inputs.
     */
    public void addFormattedToBuffer(String format, Object... inputs) {
        String formattedInput = String.format(format, inputs);
        addToBuffer(formattedInput);
    }

    /**
     * Returns {@link #bufferedOutputs} as one {@link String}.
     *
     * @return The combined output.
     */
    public String collectBuffer() {
        String output = bufferedOutputs.toString();
        clearBuffer();
        return output;
    }

    /**
     * Reads user inputs, does something and returns a back the input as a {@link String}.
     *
     * @return The users input as a {@link String}.
     */
    public abstract String readCommand();

    /**
     * Outputs a greeting to the UI.
     */
    public abstract void printGreeting();

    /**
     * Outputs a goodbye message to the UI.
     */
    public abstract void printGoodbye();

    /**
     * Outputs buffer to the UI.
     */
    public abstract void printOutput();
}
