public class Deadline extends Task{
    public Deadline(String description) {
        super(formatDescription(description));
    }

    @Override
    public String getTaskString() {
        return String.format("[D] [%s] %s\n",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String markAsDone() {
        return super.markAsDone() + String.format(" [D] [x] %s", this.getDescription());
    }

    @Override
    public String markAsUndone() {
        return super.markAsUndone() + String.format(" [D] [ ] %s",
                this.getDescription());
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
