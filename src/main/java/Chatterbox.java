import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Chatterbox {

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
    public enum Command {

    }
    final static String BOTNAME = "Chatterbox";
    final static String LINESEPERATOR = "____________________________________________________________";
    public static String greeting() {
        return String.format("""
 ____________________________________________________________
 Hello! I'm %s
 What can I do for you?
____________________________________________________________
""", Chatterbox.BOTNAME);
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

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting());
        Task[] userList = new Task[100];
        int current  = 0;
        try {
            while (true) {
                String response = scanner.nextLine();
                if (response.equals("bye")) {
                    break;
                } else if (response.equals("list")) {
                    System.out.println(Chatterbox.LINESEPERATOR);
                    System.out.println("Current Tasks in List: ");
                    for (int i = 0; i < current; i++) {
                        System.out.println(String.format(i + 1 + ". " + "[%s][%s] %s", userList[i].getTaskSymbol(), userList[i].getStatus() ? "X" : " ", userList[i].getDescription()));
                    }

                } else if (response.startsWith("mark")){
                    response = response.trim();
                    int index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                    userList[index].setStatus(true);
                    System.out.println(Chatterbox.LINESEPERATOR);
                    System.out.println("Marked task as done");
                    System.out.println(String.format("[X] %s", userList[index].getDescription()));


                } else if (response.startsWith("unmark")) {
                    response = response.trim();
                    int index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                    userList[index].setStatus(false);
                    System.out.println(Chatterbox.LINESEPERATOR);
                    System.out.println("Marked task as undone");
                    System.out.println(String.format("[ ] %s", userList[index].getDescription()));
                } else if (response.startsWith("todo")) {
                    userList[current] = new Todo(response);
                    current++;
                    System.out.println(Chatterbox.LINESEPERATOR);
                    System.out.println("Added Task to Todo");
                    System.out.println(String.format("Currently %d tasks in list", current));
                } else if (response.startsWith("deadline")) {
                    String[] parsed = parseDeadline(response);
                    userList[current] = new Deadline(parsed[0], parsed[1]);
                    current++;
                    System.out.println(Chatterbox.LINESEPERATOR);

                    System.out.println("Added Deadline to Todo");
                    System.out.println(String.format("Currently %d tasks in list", current));
                }else if (response.startsWith("event")) {
                    String[] parsed = parseEvent(response);
                    userList[current] = new Event(parsed[0], parsed[1], parsed[2]);
                    current++;
                    System.out.println(Chatterbox.LINESEPERATOR);

                    System.out.println("Added Event to Todo");
                    System.out.println(String.format("Currently %d tasks in list", current));
                } else {
//                    userList[current] = new Task(response);
//                    current++;
//                    System.out.println(Chatterbox.LINESEPERATOR);
//
//                    System.out.println("added: " + response);
//                    System.out.println(Chatterbox.LINESEPERATOR);
                    checkMessage(response);
                }
            }
        } catch (ChatterBoxError e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println(goodBye());


            scanner.close();
        }



    }
}
