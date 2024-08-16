public class Event extends Task{
    private String startDate;
    private String endDate;

    private Event(String name, String startDate, String endDate) throws CheeseException {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Factory method to ensure correct creation of Event
     * @param input String
     * @return Event
     * @throws CheeseException custom exception
     */
    public static Event of(String input) throws CheeseException {
        String[] words = input.replace("event", "").strip().split("/from");
        if (words.length < 2) throw new CheeseException("Event needs /from .... /to");
        String[] dates = words[1].split("/to");
        if (dates.length < 2) throw new CheeseException("Event needs also needs a /to");
        return new Event(words[0], dates[0].strip(), dates[1].strip());
    }

    @Override
    public String toString() {
        return "[E]" +
               super.toString() +
               "(" + startDate + "-" + endDate +")";
    }
}
