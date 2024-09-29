package chappy.task;

import org.json.simple.JSONObject;

import chappy.exception.CreateTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String from;
    private final String to;

    private final LocalDate fromLocalDate;
    private final LocalDate toLocalDate;

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

    private Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromLocalDate = null;
        this.toLocalDate = null;
    }

    private Event(String description, LocalDate fromLocalDate, LocalDate toLocalDate) {
        super(description);
        this.fromLocalDate = fromLocalDate;
        this.toLocalDate = toLocalDate;
        this.from = null;
        this.to = null;
    }

    /**
     * Returns Event object based on user's input. If options parsed from user's input are invalid,
     * null is returned. <<<<<<< HEAD
     *
     * =======
     *
     * >>>>>>> branch-A-CodingStandard If the options cannot be parsed as LocalDate objects, then
     * options are stored as String instead.
     *
     * @param input user's input.
     * @return Event object.
     */
    public static Event of(String input) throws CreateTaskException {
        String[] values;
        values = validateOptions(input);


        if (values == null) {
            return null;
        }

        LocalDate from = parseLocalDate(values[1]);
        LocalDate to = parseLocalDate(values[2]);
        if (from != null && to != null) {
            return new Event(values[0], from, to);
        } else {
            return new Event(values[0], values[1], values[2]);
        }

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
     * Returns a formatted String containing values of this Event.
     *
     * @return String containing values of this Event.
     */
    @Override
    public String toString() {
        if (this.fromLocalDate != null && this.toLocalDate != null) {
            return "[E]" + super.toString() + " (from: "
                    + fromLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                    + toLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";


    }

    private static String[] validateOptions(String input) throws CreateTaskException {
        String[] eventInput = input.trim().split("(?i)" + "event");
        if (eventInput.length < 2) {
            throw new CreateTaskException("Oh SIR! The description of an Event cannot be empty!");
        }
        boolean isValidated = true;
        String s = "";
        for (Option option : Option.values()) {
            if (eventInput[1].toLowerCase().contains(option.getKeyword())) {
                continue;
            }
            if (!s.equals("")) {
                s = s + " and ";
            }
            s = s + "\"" + option.getKeyword() + "\"";
            isValidated = false;
        }

        String[] returnValues = new String[3];
        if (!isValidated) {
            throw new CreateTaskException(
                    "Oh SIR! The " + s + " input of an Event cannot be missing!");
        } else {
            String[] s2 = eventInput[1].trim().split("/from");
            if (s2.length < 2) {
                throw new CreateTaskException(
                        "Oh SIR! The \"/from\" description of an Event cannot be empty! It must be in the format e.g. 23-09-2024 or a String");
            } else if (s2[0].trim() == "") {
                throw new CreateTaskException(
                        "Oh SIR! The description of an Event cannot be empty!");
            }
            returnValues[0] = s2[0].trim();
            String[] s3 = s2[1].trim().split("/to");
            if (s3.length < 2) {
                throw new CreateTaskException(
                        "Oh SIR! The \"/to\" description of an Event cannot be empty! It must be in the format dd-mm-yyyy or a String");
            }
            returnValues[1] = s3[0].trim();
            returnValues[2] = s3[1].trim();
        }
        return returnValues;
    }

    /**
     * Returns this Event as a JSONObject.
     *
     * @return JSONObject (containing a Event).
     */
    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "event");
        j.put("description", this.description);
        if (this.fromLocalDate != null && this.toLocalDate != null) {
            j.put("from", fromLocalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            j.put("to", toLocalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            j.put("from", from);
            j.put("to", to);
        }
        j.put("done", Boolean.toString(isDone));
        return j;
    }

    /**
     * Returns the Event object stored in the JSONObject.
     *
     * @param jsonObject JSONObject (containing a Event).
     * @return Event object.
     */
    public static Task fromJson(JSONObject jsonObject) {
        LocalDate from = parseLocalDate(jsonObject.get("from").toString());
        LocalDate to = parseLocalDate(jsonObject.get("to").toString());

        if (from != null && to != null) {
            Event event = new Event(jsonObject.get("description").toString(), from, to);
            if (Boolean.parseBoolean(jsonObject.get("done").toString())) {
                event.markAsDone();
            }
            return event;
        } else {
            Event event = new Event(jsonObject.get("description").toString(),
                    jsonObject.get("from").toString(), jsonObject.get("to").toString());
            if (Boolean.parseBoolean(jsonObject.get("done").toString())) {
                event.markAsDone();
            }
            return event;
        }
    }

    public boolean hasLocalDate() {
        return (fromLocalDate != null && toLocalDate != null ? true : false);
    }

    public boolean isBetweenDates(LocalDate startDate, LocalDate endDate) {
        if (!hasLocalDate()) {
            return false;
        }

        if (fromLocalDate.isAfter(startDate) && fromLocalDate.isBefore(endDate)) {
            return true;
        } else if (fromLocalDate.isBefore(startDate) && toLocalDate.isAfter(startDate)) {
            return true;
        } else {
            return false;
        }

    }

    public LocalDate getEventEndDate() {
        return (toLocalDate != null ? toLocalDate: null);
    }
}
