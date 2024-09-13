package tasks;

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
