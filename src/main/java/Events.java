public class Events extends Task {
    private final String fromTime;
    private final String toTime;

    public Events(String description) {
        super();
        String[] descriptionString = description.split("/");
        this.description = descriptionString[0];
        this.fromTime = descriptionString[1];
        this.toTime = descriptionString[2];
        this.type = "E";
    }

    @Override
    public String getDescription() {
        return this.description
                + "(" + this.fromTime.replaceFirst("from", "from:")
                + this.toTime.replaceFirst("to", "to:") + ")";
    }

}
