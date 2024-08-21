public class Events extends Tasks {
    private String startDate;
    private String endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(from: " + startDate + " to: " + endDate + ")";
    }

    public static Events CreateEvent(String text) throws InvalidDateException ,NoDescriptionException {
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(6, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateSubstring = text.substring(descriptionEnd + 1).trim();
        if (!dateSubstring.startsWith("from")) {
            throw new InvalidDateException(text);
        }

        int startDateEnd = dateSubstring.indexOf('/');
        String startDate = dateSubstring.substring(4, startDateEnd).trim();

        String endDateCommand = dateSubstring.substring(startDateEnd + 1);
        if (!endDateCommand.startsWith("to")) {
            throw new InvalidDateException(text);
        }
        String endDate = endDateCommand.substring(2).trim();
        return new Events(description, startDate, endDate);
    }
}


