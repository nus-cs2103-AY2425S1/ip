package bitbot;

import java.time.format.DateTimeFormatter;

public class ToDos extends Task {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    public ToDos (String description) {
        super(description);
    }

    /**
     * prints out the final display of the todo details to the user.
     * @return prints out the final display of the todo details to the user.
     */
    @Override
    public String finalString() {
        return "[T]" + super.finalString();
    }

    /**
     * prints out the final display of the todo details to the file to be stored.
     * @return prints out the final display of the todo details to the file to be stored.
     */
    @Override
    public String toFileFormat() {
        return "T|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + "NIL" + "|" + " NIL";
    }
}