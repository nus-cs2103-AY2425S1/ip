public class ToDo extends Task {
    public ToDo(String description) throws DonnaException {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }
}