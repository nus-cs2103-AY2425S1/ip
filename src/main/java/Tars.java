import java.util.ArrayList;
import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //void since method does not return anything
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
class Event extends Task {
    protected String from, to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
public class Tars {
    static String line = "    _____________________________________________";
    public static void main(String[] args) {
        //welcome/introduction message
        System.out.println(line + "\n" + "    Hello! I'm Tars\n" + "    What can I do for you" + "\n" + line);

        Scanner scanner = new Scanner(System.in); //initalising input scanner
        //String entry = scanner.nextLine(); //storing string input in a variable
        //String[] entryParts = entry.split(" "); //when mark/unmark is given as input with TASK number

        ArrayList<Task> itemsList = new ArrayList<>(); //store all input entries

        //while loop to ensure termination of programme only when "bye" input
        while (true) {
            String entry = scanner.nextLine();
            String[] entryParts = entry.split(" ");

            try {
                if(entryParts.length < 2){
                    if(entry.equals("todo") || entry.equals("deadline") || entry.equals("event")){
                        throw new TarsException(line + "\n" + "    OOPS! Describe the task/event/deadline/todo or list"
                                + "\n" + line);
                    } else if (entryParts[0].equals("list")) {
                        System.out.println(line + "\n" + "    Here are the tasks in your list:");
                        for (int i = 0; i < itemsList.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + itemsList.get(i));
                        }
                        System.out.println(line);
                    }  else if(entry.equals("bye")){
                        break;
                    } else{
                        throw new TarsException(line + "\n" + "    OOPS! Only accept a task/event/deadline/todo as input"
                                + "\n" + line);
                    }
                } else if (entryParts[0].equals("mark")) {
                    Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //to convert string format of number to Integer
                    itemsList.get(index - 1).mark(); //marking TASK as done

                    System.out.println(line);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("        " + itemsList.get(index - 1) + "\n" + line);
                } else if (entryParts[0].equals("unmark")) {
                    Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //convert str format of number to Integer
                    itemsList.get(index - 1).unmark(); //unmarking TASK as not done

                    System.out.println(line);
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("        " + itemsList.get(index - 1) + "\n" + line);
                } else if (entryParts[0].equals("todo")) {
                    StringBuilder strBuild = new StringBuilder();

                    for (int i = 1; i < entryParts.length; i++) {
                        strBuild.append(entryParts[i]).append(" ");
                    }

                    ToDos todo = new ToDos(strBuild.toString().trim());
                    itemsList.add(todo);

                    System.out.println(line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + todo);
                    System.out.println("    Now you have " + itemsList.size() + " tasks in the list");
                    System.out.println(line);
                } else if (entryParts[0].equals("deadline")) {
                    StringBuilder strBuild = new StringBuilder();
                    StringBuilder dateStr = new StringBuilder();

                    for (int i = 1; i < entryParts.length - 1; i++) {
                        if (i < entryParts.length - 2) {
                            strBuild.append(entryParts[i]).append(" ");
                        } else {
                            dateStr.append(entryParts[entryParts.length - 1]);
                        }
                    }

                    Deadline deadlineTask = new Deadline(strBuild.toString().trim(), dateStr.toString());
                    itemsList.add(deadlineTask);

                    System.out.println(line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + deadlineTask);
                    System.out.println("    Now you have " + itemsList.size() + " tasks in the list" + "\n" + line);
                } else if (entryParts[0].equals("event")) {
                    StringBuilder strBuild = new StringBuilder();
                    StringBuilder toStr = new StringBuilder();
                    StringBuilder forStr = new StringBuilder();

                    for (int i = 1; i < entryParts.length; i++) {
                        if (i < entryParts.length - 5) {
                            strBuild.append(entryParts[i]).append(" ");
                        } else if (i > entryParts.length - 5 && i < entryParts.length - 2) {
                            forStr.append(entryParts[i]).append(" ");
                        } else if (i > entryParts.length - 2) {
                            toStr.append(entryParts[i]);
                        }
                    }

                    Event eventTask = new Event(strBuild.toString(), forStr.toString().trim(), toStr.toString());
                    itemsList.add(eventTask);

                    System.out.println(line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + eventTask);
                    System.out.println("    Now you have " + itemsList.size() + " tasks in the list" + "\n" + line);
                } else if(!entryParts[0].equals("todo") || !entryParts[0].equals("deadline") || !entryParts[0].equals("event") || !entry.equals("list")){
                    throw new TarsException(line + "\n" + "    Please state a todo/deadline/event task to be recorded"
                            + "\n" + line);
                } else{
                    Task t = new Task(entry);
                    itemsList.add(t); //adding a Task to list

                    System.out.println(line);
                    System.out.println("    added: " + entry + "\n" + line);
                }
            } catch (TarsException e) {
                System.out.println(e.getMessage());
            }
        }

        //exit message when given input "bye"
        System.out.println(line + "\n" + "    Bye. Hope to see you again soon!" + "\n" + line);
    }
}
