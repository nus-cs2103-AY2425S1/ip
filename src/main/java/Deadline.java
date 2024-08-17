public class Deadline extends Task {
    private String type = "[D]";

    private static String modifyDescription(String des) {
        String[] arr = des.split(" ");
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                result += "(by: ";
            } else {
                result += arr[i] + " ";
            }
        }
        return result.strip() + ")";
    }

    public Deadline(String description) {
        super(modifyDescription(description));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
