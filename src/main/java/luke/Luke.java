package luke;

/**
 * The {@code Luke} class is the main entry point for Luke.
 * It provides a method to process user input and return appropriate responses by delegating
 * the handling of input to the {@link Ui} class.
 *
 * @see LukeUI
 */
public class Luke {
    public static void main(String[] args) {
        System.out.println();
    }
    public String getOutput(String input) {
        return Ui.handleUserInput(input);
    }
}
