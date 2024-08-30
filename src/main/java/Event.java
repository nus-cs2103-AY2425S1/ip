import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    LocalDate startDate;
    LocalDate endDate;

    public Event(String name, String[] startDate, String[] endDate) {
        super(validateString(name, startDate, endDate));

        //Valid input: (name, [from, startDate], [to, endDate])
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        this.startDate = LocalDate.parse(startDate[1].trim(), formatter);
        this.endDate = LocalDate.parse(endDate[1].trim(), formatter);
    }

    private static String validateString(String name, String[] startDate, String[] endDate) {
        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your event is all about");
        }

        if (startDate == null) {
            throw new TarsException("Add a /from command and a start date");
        } else {
            switch (startDate.length) {
                case 1:
                    if (startDate[0].equals("from")) {
                        throw new TarsException("Add a event start date");
                    } else {
                        throw new TarsException("Add the /from command");
                    }

                case 2:
                    if (startDate[0].equals("from")) {
                        if (startDate[1].isEmpty()) {
                            throw new TarsException("Add an event start date");
                        }
                    } else {
                        throw new TarsException("Add the /from command");
                    }
            }
        }

        if (endDate == null) {
            throw new TarsException("Add a /to command and a end date");
        } else {
            switch (endDate.length) {
                case 1:
                    if (endDate[0].equals("to")) {
                        throw new TarsException("Add an event end date");
                    } else {
                        throw new TarsException("Add the /to command");
                    }

                case 2:
                    if (endDate[0].equals("to")) {
                        if (endDate[1].isEmpty()) {
                            throw new TarsException("Add an event end date");
                        }
                    } else {
                        throw new TarsException("Add the /to command");
                    }
            }
        }



        return name;
    }
    @Override
    public String saveTask() {

        return String.format("E|%s|%s|%s|%s", super.getStatus(), super.getTask(), startDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")), endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }


    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
