public class Deadline extends Task{
    public Deadline(String description) {
        super(formatDescription(description));
    }

    @Override
    public String getTaskString() {
        return String.format("[D] [%s] %s\n",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        String response = String.format("Nice! I've marked this task as done:\n [D] [x] %s", this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        String response = String.format("OK, I've marked this task as not done yet:\n [D] [ ] %s",
                this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    private static String formatDescription(String input) {
        String str = input.split(" ", 2)[1];
        String[] splitBySlash = str.split("/",2);
        if (splitBySlash.length != 2) {
            return str;
        }
        String[] splitByColon = splitBySlash[1].split(" ",2);
        return splitBySlash[0] + String.format("(%s: %s)", splitByColon[0], splitByColon[1]);
    }
    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
