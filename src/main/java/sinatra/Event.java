package sinatra;

public class Event extends Task {


    private String from;
    private String to;


    public static Event newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new Event(parts[0], Boolean.parseBoolean(parts[1]), parts[2], parts[3]);


    }

    public Event(String content, Boolean status, String from, String to) {
        super(content, status);
        this.from = from;
        this.to = to;


    }

    public String getDataForStorage() {
        return "Sinatra.Event:" + super.getContent() + "," + super.isMarkedString() + "," + from + "," + to;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " " + "to: " + to + ")";
    }


}
