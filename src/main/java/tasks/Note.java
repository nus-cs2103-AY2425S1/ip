package tasks;

/**
 * A Note class for user to hold notes.
 */
public class Note extends Task {

    /**
     * Returns a Note object.
     *
     * @param description Description of note.
     */
    public Note(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveFormat() {
        return String.format(
                "N | - | %s",
                super.getDescription()
        );
    }

    /**
     * Returns Note object from storage.
     *
     * @param input String representation of Note object from save File.
     * @return Note object.
     */
    public static Task load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert parameters.length == 3;
        return new Note(parameters[2].trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[N][-] %s",
                super.getDescription()
        );
    }
}
