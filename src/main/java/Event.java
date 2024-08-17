public class Event extends Task {
    private String type = "[E]";

    private static String modifyDescription(String des) throws TaskException {
        if (des.length() == 0) {
            throw new TaskException("OH NO!!! The description of Event cannot be empty!");
        }
        String[] arr = des.split(" ");
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                result += "(from: ";
            } else if (arr[i].equals("/to")) {
                result += "to: ";
            } else {
                result += arr[i] + " ";
            }
        }
        return result.strip() + ")";
    }

    public Event(String description) throws TaskException {
        super(modifyDescription(description));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
