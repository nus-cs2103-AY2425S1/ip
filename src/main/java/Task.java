public abstract class Task {
    private final String description;
    private boolean done;

    public Task (String description) {
        this.description = description;
        this.done = false;
    }

    public String getTaskString() {
        return String.format("[%s] %s\n",done?"x" :" ", description);
    }

    public String getDescription() {
        return String.format(description);
    }
    public String markAsDone() {
        done = true;
        return "Nice! I've marked this task as done:\n";
    }

    public String markAsUndone() {
        done = false;
        return "OK, I've marked this task as not done yet:\n";
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public static int matchesUnmark(String input) {
        if (input.startsWith("unmark")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 2) {
                try {
                    return Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e){
                    return 0;
                }
            }
        }
        return 0;
    }

    //if returns 0 means no matches
    public static int matchesMark(String input) {
        if (input.startsWith("mark")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 2) {
                try {
                    return Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e){
                    return 0;
                }
            }
        }
        return 0;
    }
}
