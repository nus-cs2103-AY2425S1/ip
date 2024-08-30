import java.io.FileWriter;

public class Deadline extends Task {

    public static Deadline newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new Deadline(parts[0], Boolean.parseBoolean(parts[1]), parts[2]);


    }

    private String by;

    public Deadline(String content, Boolean status, String by) {
        super(content, status);
        this.by = by;


    }

    public String getDataForStorage() {
        return "Deadline:" + super.getContent() + "," + super.getStatusString() + "," + by;
    }


    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


}
