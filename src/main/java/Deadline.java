import org.json.simple.JSONObject;

public class Deadline extends Task {

    private final String completeBy;

    public static enum Option {
        BY("/by");
        private final String keyword;

        Option(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

    }

    public Deadline(String description, String completeBy) {
        super(description);
        this.completeBy = completeBy;

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + completeBy + ")";
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
            throw new CreateTaskException("Oh SIR! The " + s + " input of a Deadline cannot be missing!");
        } else {
            String[] s2 = input.split("/by");
            if (s2.length < 2) {
                throw new CreateTaskException("Oh SIR! The \"/by\" description of a Deadline cannot be empty!");
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "deadline");
        j.put("description", this.description);
        j.put("completeBy", completeBy);
        return j;
    }

    public static Task fromJson(JSONObject jsonObject) {
        Deadline t = new Deadline(jsonObject.get("description").toString(), jsonObject.get("completeBy").toString());
        return t;
    }
}
