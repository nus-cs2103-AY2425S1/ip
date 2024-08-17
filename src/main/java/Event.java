public class Event extends Task {
    private String type = "[E]";

    private static String modifyDescription(String des) {
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

    public Event(String description) {
        super(modifyDescription(description));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
