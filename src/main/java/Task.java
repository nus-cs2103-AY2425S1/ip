import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;


    public static Task of(String descriptions) throws MissingInformationException, InvalidInputException {
        if (descriptions.equals("todo") || descriptions.equals("deadline") || descriptions.equals("event")){
            throw new MissingInformationException("description", descriptions);
        }
        if (descriptions.startsWith("todo ")) {
            if (descriptions.length() <= 5 || descriptions.charAt(5) == ' ') {
                throw new MissingInformationException("description", "todo");
            }
            return new ToDo(false, descriptions.substring(5));
        } else if (descriptions.startsWith("deadline ")) {
            if (descriptions.length() <= 9 || descriptions.charAt(9) == ' ') {
                throw new MissingInformationException("description", "deadline");
            }
            String[] strings = descriptions.substring(9).split("/");
            if (strings.length < 2 || !strings[1].startsWith("by ")) {
                throw new MissingInformationException("by time", "deadline");
            }
            return new DeadLine(false, strings[0],strings[1].substring(3));
        } else if (descriptions.startsWith("event ")) {
            if (descriptions.length() <= 6 || descriptions.charAt(6) == ' ') {
                throw new MissingInformationException("description", "event");
            }
            String[] strings = descriptions.substring(6).split("/");
            if (strings.length < 2 || !strings[1].startsWith("from ")) {
                throw new MissingInformationException("from time", "event");
            } else if (strings.length < 3 || !strings[2].startsWith("to ")) {
                throw new MissingInformationException("to time", "event");
            }
            return new Event(false, strings[0],strings[1].substring(5),strings[2].substring(3));
        } else {
            throw new InvalidInputException();
        }
    }

    public static Task read(String[] strings) throws CorruptedFileException{
        if (strings.length <= 2) {
            throw new CorruptedFileException("");
        } else if (strings[0].equals("T") && strings.length == 3) {
            return new ToDo(parseIcon(strings[1]), strings[2]);
        } else if (strings[0].equals("D") && strings.length == 4) {
            return new DeadLine(parseIcon(strings[1]), strings[2], strings[3]);
        } else if (strings[0].equals("E") && strings.length == 5) {
            return new Event(parseIcon(strings[1]), strings[2], strings[3], strings[4]);
        } else {
            throw new CorruptedFileException("");
        }
    }

    public abstract String getStorageMessage();

    private Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    private static boolean parseIcon(String s) throws CorruptedFileException {
        if (s.equals("X")) {
            return true;
        } else if (s.equals(" ")) {
            return false;
        } else {
            throw new CorruptedFileException("");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(),description);
    }



    public void mark() throws IncorrectStateException{
        if (this.isDone) {
            throw new IncorrectStateException("mark");
        }
        this.isDone = true;
    }

    public void unmark() throws IncorrectStateException{
        if (!this.isDone) {
            throw new IncorrectStateException("unmark");
        }
        this.isDone = false;
    }

    public abstract boolean isRelatedToDate(LocalDate date);

    private static class ToDo extends Task {

        public ToDo(boolean isDone, String description) {
            super(isDone, description);
        }

        @Override
        public String toString() {
            return String.format("[T][%s] %s", getStatusIcon(), description);
        }

        @Override
        public String getStorageMessage() {
            return String.format("T | %s | %s", getStatusIcon(), description);
        }

        @Override
        public boolean isRelatedToDate(LocalDate date) {
            return false;
        }
    }

    private static class DeadLine extends Task {
        private LocalDate deadLine;

        public DeadLine(boolean isDone,String description, String deadLine) {
            super(isDone, description);
            this.deadLine = LocalDate.parse(deadLine);
        }

        @Override
        public String toString() {
            return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, deadLine);
        }

        @Override
        public String getStorageMessage() {
            return String.format("D | %s | %s | %s", getStatusIcon(), description, deadLine);
        }

        @Override
        public boolean isRelatedToDate(LocalDate date) {
            return date.isBefore(deadLine);
        }
    }

    private static class Event extends Task {
        private LocalDate from;
        private LocalDate to;


        private Event(boolean isDone, String description, String from, String to) {
            super(isDone, description);
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);

        }

        @Override
        public String toString() {
            return String.format("[E][%s] %s (from: %s to: %s)",getStatusIcon(),description,from,to);
        }

        @Override
        public String getStorageMessage() {
            return String.format("E | %s | %s | %s | %s", getStatusIcon(), description, from, to);
        }

        public boolean isRelatedToDate(LocalDate date) {
            return date.isAfter(from) && date.isBefore(to);

        }
    }

    //...
}
