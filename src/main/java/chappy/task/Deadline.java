package chappy.task;

import org.json.simple.JSONObject;

import chappy.exception.CreateTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final String completeBy;

    private final LocalDate byLocalDate;

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

    private Deadline(String description, String completeBy) {
        super(description);
        this.completeBy = completeBy;
        this.byLocalDate = null;

    }

    private Deadline(String description, LocalDate byLocalDate) {
        super(description);
        this.byLocalDate = byLocalDate;
        this.completeBy = null;
    }

    /**
     * Returns Deadline object based on user's input. If options parsed from user's input are
     * invalid, null is returned.
     *
     * If the options cannot be parsed as LocalDate objects, then options are stored as String
     * instead.
     *
     * @param input user's input.
     * @return Deadline object.
     */
    public static Deadline of(String input) {
        String[] values;
        try {
            values = validateOptions(input);

        } catch (CreateTaskException e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (values != null) {
            LocalDate by = parseLocalDate(values[1]);
            if (by != null) {
                return new Deadline(values[0], by);
            } else {
                return new Deadline(values[0], values[1]);
            }
        }

        return null;
    }

    private static LocalDate parseLocalDate(String input) throws DateTimeParseException {
        try {
            LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return localDate;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns a formatted String containing values of this Deadline.
     *
     * @return String containing values of this Deadline.
     */
    @Override
    public String toString() {
        if (this.byLocalDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + byLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + completeBy + ")";
        }

    }

    private static String[] validateOptions(String input) throws CreateTaskException {
        String[] eventInput = input.trim().split("(?i)" + "deadline");
        if (eventInput.length < 2) {
            throw new CreateTaskException("Oh SIR! The description of an Event cannot be empty!");
        }

        boolean isValidated = true;
        String s = "";
        for (Option option : Option.values()) {
            if (!eventInput[1].toLowerCase().contains(option.getKeyword())) {

                if (!s.equals("")) {
                    s = s + " and ";
                }
                s = s + "\"" + option.getKeyword() + "\"";
                isValidated = false;
            }
        }
        String[] returnValues = new String[2];
        if (!isValidated) {
            throw new CreateTaskException(
                    "Oh SIR! The " + s + " input of a Deadline cannot be missing!");
        } else {
            String[] s2 = eventInput[1].trim().split("/by");
            if (s2.length < 2) {
                throw new CreateTaskException(
                        "Oh SIR! The \"/by\" description of a Deadline cannot be empty! It must be in the format e.g. 23-09-2024 or a String");
            } else if (s2[0].trim() == "") {
                throw new CreateTaskException(
                        "Oh SIR! The description of an Event cannot be empty!");
            }
            returnValues[0] = s2[0].trim();
            returnValues[1] = s2[1].trim();
        }
        return returnValues;
    }

    /**
     * Returns this Deadline as a JSONObject.
     *
     * @return JSONObject (containing a Deadline).
     */
    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "deadline");
        j.put("description", this.description);
        if (this.byLocalDate != null) {
            j.put("completeBy", byLocalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        } else {
            j.put("completeBy", completeBy);
        }
        j.put("done", Boolean.toString(isDone));
        return j;
    }

    /**
     * Returns the Deadline object stored in the JSONObject.
     *
     * @param jsonObject JSONObject (containing a Deadline).
     * @return Deadline object.
     */
    public static Task fromJson(JSONObject jsonObject) {
        LocalDate by = parseLocalDate(jsonObject.get("completeBy").toString());
        if (by != null) {
            Deadline deadline = new Deadline(jsonObject.get("description").toString(), by);
            if (Boolean.parseBoolean(jsonObject.get("done").toString())) {
                deadline.markAsDone();
            }
            return deadline;
        } else {
            Deadline deadline = new Deadline(jsonObject.get("description").toString(),
                    jsonObject.get("completeBy").toString());
            if (Boolean.parseBoolean(jsonObject.get("done").toString())) {
                deadline.markAsDone();
            }
            return deadline;
        }
    }
}
