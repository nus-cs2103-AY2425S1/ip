import java.time.DateTimeException;
import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Chatterbox {
    final static String HISTFILE = Paths.get(System.getProperty("user.dir"),"data" , "command1.txt").toString();
    final static String BOTNAME = "Chatterbox";
    final static String LINESEPERATOR = "____________________________________________________________";

    private static final DateTimeFormatter DASHFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter SLASHFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final DateTimeFormatter DASHONLYDATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter SLASHONLYDATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[] {DASHFORMATTER, SLASHFORMATTER};
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = new DateTimeFormatter[] {DASHONLYDATE, SLASHONLYDATE};
    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");


    private static LocalDateTime parseDateTime(String dateTimeString) {
        /**
         * Takes in a string, trims it, and parses LocalDateTime if possible, if not null
         *
         * @param dateTimeString String that should contain a date time
         * @return LocalDateTime representing time in string
         * @throws None
         */
        System.out.println("called");

        dateTimeString = dateTimeString.trim();
        System.out.println(dateTimeString);
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {


                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                //do nothing, try next one
            }
        }
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                return LocalDate.parse(dateTimeString, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;

        public static Command parseCommand(String text) {


            if (text.startsWith("bye")) {
                return BYE;
            } else if (text.startsWith("list")) {
                return LIST;
            } else if (text.startsWith("mark")) {
                return MARK;
            } else if (text.startsWith("unmark")) {
                return UNMARK;
            } else if (text.startsWith("todo")) {
                return TODO;
            } else if (text.startsWith("deadline")) {
                return DEADLINE;
            } else if (text.startsWith("event")) {
                return EVENT;
            } else if (text.startsWith("delete")) {
                return DELETE;
            } else {
                return INVALID;
            }

        }
    }

    public static class ChatterBoxError extends Exception {
        public ChatterBoxError (String message) {
            super(message);
        }
    }
    public static class ChatterBoxNoInput extends ChatterBoxError {
        public ChatterBoxNoInput (String message) {
            super(message);
        }
    }

    public static class ChatterBoxUnknownCommand extends ChatterBoxError {
        public ChatterBoxUnknownCommand (String message) {
            super(message);
        }
    }

    public static class ChatterBoxMissingParameter extends ChatterBoxError {
        public ChatterBoxMissingParameter(String para) {
            super("Missing parameter: " + para);
        }
    }
    public static void checkMessage(String msg) throws ChatterBoxUnknownCommand{
        throw new ChatterBoxUnknownCommand("Error: Unknown command");
    }

    public static String greeting() {
        return String.format("""
____________________________________________________________
 Hello! I'm %s
 What can I do for you?""", Chatterbox.BOTNAME);
    }

    public static String goodBye() {
        return """
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
""";
    }


    //used to get the index number for mark and unmark
    private static int extractNum(String input) {
        int length = input.length();
        StringBuilder numberBuild = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                numberBuild.insert(0, currentChar);
            } else {
                break;
            }
        }
        return Integer.parseInt(numberBuild.toString());
    }




    private static class Task {
        private Boolean status;
        private String desc;


        public Task(String desc) throws ChatterBoxNoInput{
            if (desc.trim().isEmpty()) {
                throw new ChatterBoxNoInput("Error: No input for task");
            }
            this.status = false;
            this.desc = desc;
        }

        public String getDescription() {
            return this.desc;
        }
        public Boolean getStatus() {
            return this.status;
        }

        public void setStatus(Boolean stat) {
            this.status = stat;
        }
        public String getTaskSymbol() { return "";}

        @Override
        public String toString() {
            return this.getDescription();
        }
    }

    protected static String parseTODO(String desc) {
        return desc.substring(4).trim();
    }
    private static class Todo extends Task {


        public Todo(String desc) throws ChatterBoxNoInput{
            super(parseTODO(desc));

        }

        @Override
        public String getTaskSymbol() { return "T"; }

    }

    /** used to parse deadlines
     *
     * @param String of format deadline text /by text
     * @return String[] with the [0] as the description and [1] as by {text}
     * @throws None, does not throw any exceptions
     */
    private static String[] parseDeadline(String desc) throws ChatterBoxMissingParameter{


        int endDate = desc.indexOf("/by");
        if (endDate < 0) {
            throw new ChatterBoxMissingParameter("Deadline date");
        }
        StringBuilder plainDesc = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        for (int i = 9; i < desc.length(); i++) {
            if (i < endDate) {
                plainDesc.append(desc.charAt(i));

            } else {
                if (desc.charAt(i) != '/') {
                    deadline.append(desc.charAt(i));
                }
            }
        }
        return new String[] {plainDesc.toString(), deadline.toString()} ;
    }

    private static class Deadline extends Task {
        private final LocalDateTime dueDateObj;
        private final String dueDate;

        public Deadline(String desc, String dueDate) throws ChatterBoxNoInput{
            super(desc);
            this.dueDate =dueDate;
            this.dueDateObj = null;


        }

        public Deadline(String desc, LocalDateTime dueDateObj) throws ChatterBoxNoInput{
            super(desc);
            this.dueDateObj = dueDateObj;
            this.dueDate = null;
        }

        @Override
        public String getTaskSymbol() {
            return "D";
        }
        @Override
        public String getDescription() {
            if (this.dueDateObj != null) {
                return super.getDescription() + String.format("( by %s) ", this.dueDateObj.format(PRINTDATEFORMATTER));
            }
            return super.getDescription() + String.format(" ( %s )", this.dueDate);
        }

    }
    //when response start with event
    /** Parses the event string for the desc, from and to time
     *
     * @param desc takes in a string of format text /from text /to text
     * @return String[] with 0 being the first text, 1 the from text and 2 being the to text
     * @throws ChatterBoxMissingParameter if any of parameters not detected
     */
    private static String[] parseEvent(String desc) throws ChatterBoxMissingParameter{


        int fromStart = desc.indexOf("/from");
        if (fromStart < 0) {
            throw new ChatterBoxMissingParameter("Event Start Date");
        }
        int toStart = desc.indexOf("/to");
        if (toStart < 0) {
            throw new ChatterBoxMissingParameter("Event End Date");
        }
        StringBuilder plainDesc = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        //start with 5 to go past event
        for (int i = 5; i < desc.length(); i++) {
            if (i < fromStart) {
                plainDesc.append(desc.charAt(i));
            } else if (i < toStart) {
                if (desc.charAt(i) != '/') {
                    startDate.append(desc.charAt(i));

                }
            } else {
                if (desc.charAt(i) != '/') {
                    endDate.append(desc.charAt(i));

                }
            }
        }
        return new String[] {plainDesc.toString(), startDate.toString(), endDate.toString()};
    }
    private static class Event extends Task {

        private final LocalDateTime startDateObj;
        private final String startDate;

        private final LocalDateTime endDateObj;
        private final String endDate;




        public Event(String desc, String startDate, String endDate) throws ChatterBoxNoInput{
            super(desc);
            this.startDate = startDate;
            this.endDate = endDate;
            this.startDateObj = null;
            this.endDateObj = null;
        }

        public Event(String desc, LocalDateTime startDateObj, LocalDateTime endDateObj) throws ChatterBoxNoInput{
            super(desc);
            this.startDate = null;
            this.endDate = null;
            this.startDateObj = startDateObj;
            this.endDateObj = endDateObj;
        }

        public String getStartDate() {
            return startDate;
        }

        private String getEndDate() {
            return this.endDate;
        }

        @Override
        public String getTaskSymbol() {
            return "E";
        }
        @Override
        public String getDescription() {
            if (this.startDateObj != null && this.endDateObj != null) {
                return super.getDescription() + String.format("( from %s to %s )", this.startDateObj.format(PRINTDATEFORMATTER)
                , this.endDateObj.format(PRINTDATEFORMATTER));
            }
            return super.getDescription() + String.format(" ( %s %s )", this.startDate, this.endDate);
        }
    }

    /**
     * Used to check for directory used to store data and create if not present,
     * @param None
     */
    private static void checkDirectory() {

        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir"), "data"));
        } catch (IOException e) {
            System.out.println("Error creating data directory: " + e.getMessage());
        }
    }

    /** Saves the tasks to a file based on the userList, creates a new directory data
     *
     * @param userList contains a list of Task objects
     */
    private static void saveHistory(ArrayList<Task> userList) {



        checkDirectory();

        File file = new File(HISTFILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);

            StringBuilder history = new StringBuilder();
            for (int i = 0; i < userList.size(); i++) {

                Task currentTask = userList.get(i);
//                System.out.println(currentTask.getDescription());
                String taskStr = String.format("%s | %s | %s", currentTask.getTaskSymbol(), currentTask.getStatus()? "X" : " ", currentTask.getDescription());
                history.append(taskStr);
                history.append(System.lineSeparator());
            }
            writer.write(history.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Error has occurred " + e.getMessage());

        }
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting());
        ArrayList<Task> userList = new ArrayList<Task>();
        int current  = 0;
        try {
            while (true) {
                String response = scanner.nextLine();
                Command command = Command.parseCommand(response);
                int index;
                switch (command){

                    case BYE: return;

                    case LIST:
                        System.out.println(Chatterbox.LINESEPERATOR);
                        System.out.println("Current Tasks in List: ");
                        for (int i = 0; i < userList.size(); i++) {
                            System.out.println(String.format(i + 1 + ". " + "[%s][%s] %s", userList.get(i).getTaskSymbol(), userList.get(i).getStatus() ? "X" : " ", userList.get(i).getDescription()));
                        }
                        break;

                    case MARK:
                        response = response.trim();
                        index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                        userList.get(index).setStatus(true);
                        System.out.println(Chatterbox.LINESEPERATOR);
                        System.out.println("Marked task as done");
                        System.out.println(String.format("[X] %s", userList.get(index).getDescription()));
                        break;

                    case UNMARK:
                        response = response.trim();
                        index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                        userList.get(index).setStatus(false);
                        System.out.println(Chatterbox.LINESEPERATOR);
                        System.out.println("Marked task as undone");
                        System.out.println(String.format("[ ] %s", userList.get(index).getDescription()));
                        break;

                    case TODO:
                        userList.add(new Todo(response));
                        current++;
                        System.out.println(Chatterbox.LINESEPERATOR);
                        System.out.println("Added Task to Todo");
                        System.out.println(String.format("Currently %d tasks in list", userList.size()));
                        break;

                    case DEADLINE:
                        String[] parsed = parseDeadline(response);

                        System.out.println(Chatterbox.LINESEPERATOR);
                        LocalDateTime deadlineDate = parseDateTime(parsed[1].substring(2));

                        if (deadlineDate == null) {
                            userList.add(new Deadline(parsed[0], parsed[1]));

                        } else {
                            //add back by for string

                            userList.add(new Deadline(parsed[0], deadlineDate));
                        }

                        current++;
                        System.out.println("Added Deadline to Todo");
                        System.out.println(String.format("Currently %d tasks in list", userList.size()));
                        break;
                    case EVENT:
                        String[] eventParsed = parseEvent(response);

                        LocalDateTime startDate = parseDateTime(eventParsed[1].substring(4)); //from 4
                        LocalDateTime endDate = parseDateTime(eventParsed[2].substring(2));
                        if (startDate == null || endDate == null) {
                            userList.add(new Event(eventParsed[0], eventParsed[1], eventParsed[2]));

                        } else {
                            userList.add(new Event(eventParsed[0], startDate, endDate));
                        }
                        current++;
                        System.out.println(Chatterbox.LINESEPERATOR);

                        System.out.println("Added Event to Todo");
                        System.out.println(String.format("Currently %d tasks in list", userList.size()));
                        break;
                    case DELETE:
                        response = response.trim();
                        int delIndex = Chatterbox.extractNum(response) - 1;
                        System.out.println(LINESEPERATOR);
                        System.out.println("Removing Task: ");
                        System.out.println(userList.get(delIndex).toString());
                        userList.remove(delIndex);
                        System.out.println(String.format("List has %d tasks", userList.size()));
                        break;
                    case INVALID:
                        checkMessage(response);
                        break;
                }
//                System.out.println("saving");
                saveHistory(userList);

            }
        } catch (ChatterBoxError e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println(goodBye());		
            scanner.close();
        }



    }
}
