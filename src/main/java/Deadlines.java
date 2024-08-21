public class Deadlines extends Tasks {

    private String date;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    protected String typeIcon() {
        return "[D]";
    }

    protected static Deadlines createDeadline(String text) throws NoDescriptionException, InvalidDateException{
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(9, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateCommand = text.substring(descriptionEnd + 1).trim();
        if (!dateCommand.startsWith("by")) {
            throw new InvalidDateException(text);
        }
        String date = dateCommand.substring(2).trim();
        return new Deadlines(description, date);
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(by:" + date + ")";
    }

}
