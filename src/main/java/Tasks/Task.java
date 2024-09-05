package Tasks;

import java.util.Objects;

public abstract class Task {

    private String input;
    private boolean done;
    String line = "____________________________________________________________\n";

    public Task(String input) {
        this.input = input;
        this.done = false;
    }

    public String addedString() {
        return "Got it. I've added this task:\n" + displayTask();
    }

    public String displayTask() {
        if (done) {
            return "[X] " + this.input + "\n";
        } else {
            return "[ ] " + this.input + "\n";
        }
    }

    public String getInput() {
        return this.input;
    }

    public boolean getDone() {
        return this.done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String markedNotification() {
        return line + "Nice! I've marked this task as done:\n"
                + this.displayTask() + line;
    }

    public void markAsUndone() {
        this.done = false;
        System.out.println(line + "OK, I've marked this task as not done yet:\n"
                + this.displayTask() + line);
    }

    public static String formatDate(String string) {
        String[] strings = string.split(" ");
        String monthInWords = strings[0];
        String dateOfMonth = strings[1];
        String year = strings[2];
        String[] hourMinute = strings[3].split(":");
        String time = hourMinute[0] + hourMinute[1];
        int month = Objects.equals(monthInWords, "Jan") ? 1
                : Objects.equals(monthInWords, "Feb") ? 2
                : Objects.equals(monthInWords, "Mar") ? 3
                : Objects.equals(monthInWords, "Apr") ? 4
                : Objects.equals(monthInWords, "May") ? 5
                : Objects.equals(monthInWords, "Jun") ? 6
                : Objects.equals(monthInWords, "Jul") ? 7
                : Objects.equals(monthInWords, "Aug") ? 8
                : Objects.equals(monthInWords, "Sep") ? 9
                : Objects.equals(monthInWords, "Oct") ? 10
                : Objects.equals(monthInWords, "Nov") ? 11
                : Objects.equals(monthInWords, "Dec") ? 12
                : -1;
        return dateOfMonth + "/" + month + "/" + year + " " + time;
    }
    public static Task intepreteTask(String description, String type) {
        if (Objects.equals(type, "T")) {    // To Do
            return new ToDo(description);
        } else if (Objects.equals(type, "D")) {     // Tasks.Deadline
            String input = description.split(" \\(by: ")[0];
            String deadlineInWords = description.split(" \\(by: ")[1]
                    .split("\\)")[0];
            String deadline = formatDate(deadlineInWords);
            return new Deadline(input, deadline);

        } else {        // Tasks.Event
            String input = description.split("\\(from: ")[0];
            String period = description.split("\\(from: ")[1];
            String startInWords = period.split(" to: ")[0];
            String endInWords = period.split(" to: ")[1];
            String start = formatDate(startInWords);
            String end = formatDate(endInWords);
            return new Event(input, start, end);
        }
    }

    /**
     * Returns true if this task name contains the keyword, false if otherwise
     *
     * @param keyword A string that the user is searching for
     * @return boolean of whether the task name contains the keyword or not
     */
    public boolean containsKeyword(String keyword) {
        return this.input.contains(keyword);
    }
}
