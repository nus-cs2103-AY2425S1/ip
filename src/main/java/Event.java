public class Event extends Task{
    public Event(String description) {
        super(formatDescription(description));
    }

    @Override
    public String getTaskString() {
        return String.format("[E] [%s] %s\n",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        String response = String.format("Nice! I've marked this task as done:\n [E] [x] %s", this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        String response = String.format("OK, I've marked this task as not done yet:\n [E] [ ] %s",
                this.getDescription());
        ScoobyDoo.printFormattedResponse(response);
    }

    private static String formatDescription(String input) {
        String str = input.split(" ", 2)[1];
        String[] splitBySlash = str.split("/",3);
        if (splitBySlash.length != 3) {
            return str;
        }
        String[] splitByColon1 = splitBySlash[1].split(" ",2);
        String[] splitByColon2 = splitBySlash[2].split(" ", 2);
        return String.format("%s (%s: %s %s: %s)",
                splitBySlash[0], splitByColon1[0],splitByColon1[1], splitByColon2[0],splitByColon2[1]);
    }
    public static boolean matchEvent(String s) {
        return s.startsWith("event");
    }
}
