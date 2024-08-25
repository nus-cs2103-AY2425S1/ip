public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
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

    public static String checkDeadlineFormat(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }
        String[] splitBySlash = splitDeadline[1].split("/",2);
        if (splitBySlash.length != 2) {
            throw new InputFormatException("Oops! I need a /by regex to save your deadline task");
        }
        String[] splitByColon = splitBySlash[1].split(" ",2);
        return splitBySlash[0] + String.format("(%s: %s)", splitByColon[0], splitByColon[1]);
    }

    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
