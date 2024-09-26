package bob.task;

import bob.exception.LineCorruptedException;

import java.util.Arrays;

public class Todo extends Task {
    public static final char ENCODED_LETTER = 'T';

    public Todo(String description, String... tags) {
        super(description, tags);
    }

    @Override
    public String encode() {
        // format: <isDone><len(desc)#4><desc><tag tag ...>
        StringBuilder str = new StringBuilder();

        str.append(this.isDone ? "1" : "0");
        str.append(String.format("%04d", this.description.length()));
        str.append(this.description);

        String tagsAsString = this.tags.stream().reduce("", (s, tag) -> s + " " + tag).trim();
        str.append(tagsAsString);

        return str.toString();
    }

    public static Task decode(String encodedString) throws LineCorruptedException {
        // format: <isDone><len(desc)#4><desc><tag tag ...>

        Task task;
        try {
            task = getTask(encodedString);
        } catch (IndexOutOfBoundsException e) {
            throw new LineCorruptedException();
        }

        if (encodedString.charAt(0) == '1') {
            task.mark();
        } else if (encodedString.charAt(0) != '0') {
            throw new LineCorruptedException();
        }

        return task;
    }

    private static Task getTask(String encodedString) {
        int descLength = Integer.parseInt(encodedString.substring(1, 5));
        String desc = encodedString.substring(5, 5 + descLength);

        String tagsAsString = encodedString.substring(5 + descLength);
        String[] tags = Arrays.stream(tagsAsString.split(" "))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        return new Todo(desc, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
