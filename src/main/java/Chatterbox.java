import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
public class Chatterbox {

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

    final static String HISTFILE = Paths.get(System.getProperty("user.dir"),"data" , "command1.txt").toString();
    final static String BOTNAME = "Chatterbox";
    final static String LINESEPERATOR = "____________________________________________________________";
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

        private final String dueDate;

        public Deadline(String desc, String dueDate) throws ChatterBoxNoInput{
            super(desc);
            this.dueDate =dueDate;


        }

        @Override
        public String getTaskSymbol() {
            return "D";
        }
        @Override
        public String getDescription() {
            return super.getDescription() + String.format(" ( %s )", this.dueDate);
        }

    }
    //when response start with event
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
        private final String startDate;
        private final String endDate;



        //TODO: Change to abstract
        public Event(String desc, String startDate, String endDate) throws ChatterBoxNoInput{
            super(desc);
            this.startDate = startDate;
            this.endDate = endDate;

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
            return super.getDescription() + String.format(" ( %s %s )", this.startDate, this.endDate);
        }
    }

    private static void checkDirectory() {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir"), "data"));
        } catch (IOException e) {
            System.out.println("Error creating data directory: " + e.getMessage());
        }
    }
    private static void saveHistory(ArrayList<Task> userList) {
        checkDirectory();
//        System.out.println("Checked");
//        System.out.println(HISTFILE);
        File file = new File(HISTFILE);
//        System.out.println("created");
        try {
            if (!file.exists()) {
//                System.out.println("creatign file");
                file.createNewFile();
            }
//            System.out.println("writing");
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
                        userList.add(new Deadline(parsed[0], parsed[1]));
                        current++;
                        System.out.println(Chatterbox.LINESEPERATOR);

                        System.out.println("Added Deadline to Todo");
                        System.out.println(String.format("Currently %d tasks in list", userList.size()));
                        break;
                    case EVENT:
                        String[] eventParsed = parseEvent(response);
                        userList.add(new Event(eventParsed[0], eventParsed[1], eventParsed[2]));
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
