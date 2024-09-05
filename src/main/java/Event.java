import org.json.simple.JSONObject;

public class Event extends Task {

    private final String from;
    private final String to;

    public static enum Option {
        FROM("/from"), TO("/to");
        private final String keyword;

        Option(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static void validateOptions(String input) throws CreateTaskException {
        boolean isValidated = true;
        String s = "";
        for (Option option : Option.values()) {
            if (!input.toLowerCase().contains(option.getKeyword())) {

                if (!s.equals("")) {
                    s = s + " and ";
                }
                s = s + "\"" + option.getKeyword() + "\"";
                isValidated = false;
            }
        }
        if (!isValidated) {
            throw new CreateTaskException("Oh SIR! The " + s + " input of an Event cannot be missing!");
        } else {
            String[] s2 = input.split("/from");
            if (s2.length < 2) {
                throw new CreateTaskException("Oh SIR! The \"/from\" description of an Event cannot be empty!");
            }
            String[] s3 = s2[1].split("/to");
            if (s3.length < 2) {
                throw new CreateTaskException("Oh SIR! The \"/to\" description of an Event cannot be empty!");
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "event");
        j.put("description", this.description);
        j.put("from", from);
        j.put("to", to);
        return j;
    }

    public static Task fromJson(JSONObject jsonObject) {
        Event t = new Event(jsonObject.get("description").toString(), jsonObject.get("from").toString(), jsonObject.get("to").toString());
        return t;
    }
}
