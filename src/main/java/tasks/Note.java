package tasks;

/**
 * A Note class for user to hold notes.
 */
public class Note extends Task {
    public Note(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "N | - | %s",
                super.getDescription()
        );
    }

    /**
     * Converts a String input to a Note instance object.
     *
     * @param input from saved text file.
     * @return Note object.
     */
    public static Task load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert parameters.length == 3;
        return new Note(parameters[2].trim());
    }

    @Override
    public String toString() {
        return String.format(
                "[N][-] %s",
                super.getDescription()
        );
    }
}
