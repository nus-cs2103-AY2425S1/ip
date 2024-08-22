import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Strand {
    private static boolean running = false;
    private static final String horizontalLine = "----------------------------------------><>";
    private static final ArrayList<Task> strandList = new ArrayList<>();
    private static void print(String s) {
        System.out.println(s.indent(4));
    }
    private static void output(String s) {
        System.out.println(horizontalLine.indent(4));
        print(s);
        System.out.println(horizontalLine.indent(4));
    }
    private static void chatStart() {
        running = true;
        String nameLogo = """
                 ____ _____ ____      _    _   _ ____ \s
                / ___|_   _|  _ \\    / \\  | \\ | |  _ \\ \s
                \\___ \\ | | | |_) |  / _ \\ |  \\| | | | |\s
                 ___) || | |  _ <  / ___ \\| |\\  | |_| |\s
                |____/ |_| |_| \\_\\/_/   \\_\\_| \\_|____/\s
                """;
        output("Hello from \n" + nameLogo + "\nWhat can I do for you?");
    }

    private static String listAll() {
        return(
                strandList.stream()
                        .map((x) ->
                                (strandList.indexOf(x)+1) + "." + x.getStatusIcon() + "\n")
                        .reduce((a, b) -> a + b).orElse("")
        );
    }

    private static void mark(String input) {
        String[] split = input.split("\\s+");
        if(split.length < 2) {
            output("Please input a number after " + split[0] +  ". (e.g. mark 2).");
        } else {
            try {
                Task t = strandList.get(Integer.parseInt(split[1])-1);
                String str;
                if(Objects.equals(split[0], "mark")) {
                    t.markAsDone();
                    str = "Nice! I've marked this task as done:\n";
                } else {
                    t.markAsNotDone();
                    str = "OK, I've marked this task as not done yet:\n";
                }
                output(str + t.getStatusIcon());

            } catch (NumberFormatException e) {
                output("Please input a number after " + split[0]);
            } catch (IndexOutOfBoundsException e) {
                output("Please input a number which is in range. \n" + listAll());

            }

        }
    }

    private static void addTask(String input) {
        String type = input.split(" ")[0];
        String desc = input.split(" ", 2)[1];
        switch (type.toLowerCase()) {
            case "todo": {
                strandList.add(new Todo(desc));
                break;
            }
            case "deadline": {
                String description = desc.substring(0,desc.indexOf(" /by "));
                String deadline = desc.substring(desc.indexOf(" /by ")+5);
                strandList.add(new Deadline(description, deadline));
                break;
            }
            case "event":{
                String description = desc.substring(0,desc.indexOf(" /from "));
                String start = desc.substring(desc.indexOf(" /from ")+7, desc.indexOf(" /to "));
                String end = desc.substring(desc.indexOf(" /to ")+5);
                strandList.add(new Event(description, start, end));
                break;
            }
        }
        output("Got it. I've added this task:\n  "
                + strandList.get(strandList.size()-1).getStatusIcon()
                + "\nNow you have " + strandList.size() + " tasks in the list.");
    }

    private static void inputs(String input) {
        if(input.equalsIgnoreCase("bye")) {
            output("Bye. Hope to see you again soon!");
            running = false;
        } else if (input.equalsIgnoreCase("list")) {
            output(listAll());
        } else if (input.toLowerCase().startsWith("mark") ||
                input.toLowerCase().startsWith("unmark")) {
            mark(input);

        } else if (input.toLowerCase().startsWith("todo") ||
                input.toLowerCase().startsWith("deadline") ||
                input.toLowerCase().startsWith("event")) {
            addTask(input);
        } else {
            output("added: " + input + "\n");
            strandList.add(new Task(input));
        }
    }
    public static void main(String[] args) {
        chatStart();
        while(running) {
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            inputs(userInput);
        }
    }
}