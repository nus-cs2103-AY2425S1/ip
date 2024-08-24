import hoodini.Input;

/**
 * Stub for Input class.
 */
public class InputStub extends Input {
    private String stub;

    /**
     * Constructor for InputStub.
     * @param input Test String.
     */

    public InputStub(String input) {
        super(input);
        this.stub = input;

    }

    @Override
    public String toString() {
        return this.stub;
    }
}
