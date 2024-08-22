import java.util.ArrayList;
import java.util.Scanner;

public class Nameless {
    private static final String line = "______________________________________________________________";
    private static final String name = "Nameless";
    private static final String greetings = "Hello, I'm " + name + "\n" + "What can I do for you?";
    private static final String goodbye = "Bye. Hope to see you again!";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int splitGetNum(String input){
        String[] words = input.split(" ");
        return Integer.parseInt(words[1]) - 1;
    }

    private static String splitGetWords(String input){
        String[] words = input.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
    public static void main(String[] args) throws DukeException {
        System.out.println(line + "\n" + greetings + "\n" + line);
        String temp;
        Scanner sc = new Scanner(System.in);

        while(true){
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    //list tasking
                    System.out.println(line + "\n Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        //print out all tasking
                        System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(line);
                } else if (input.matches("mark \\d+")) {
                    System.out.println(line);
                    tasks.get(splitGetNum(input)).markTask();
                    System.out.println(line);
                } else if (input.matches("unmark \\d+")) {
                    System.out.println(line);
                    tasks.get(splitGetNum(input)).unMarkTask();
                    System.out.println(line);
                } else if (input.matches("deadline(?: .+)?")) {
                    temp = splitGetWords(input);
                    String[] words = temp.split(" /by ");
                    if (words.length < 2) {
                        throw new DukeException("incorrect format use 'deadline <task> /by <date>'");
                    }
                    tasks.add(new Deadline(words[0], words[1]));
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks.get(tasks.size() - 1).toString() + "\n" +
                            "Now you have " + tasks.size() + " task left \n" + line);
                } else if (input.matches("event(?: .+)?")) {
                    temp = splitGetWords(input);
                    String[] words = temp.split(" /from | /to ");
                    if (words.length != 3) {
                        throw new DukeException("incorrect format use 'event <task> /from <date> /to <date>'");
                    }
                    tasks.add(new Event(words[0], words[1], words[2]));
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks.get(tasks.size() - 1).toString() + "\n" +
                            "Now you have " + tasks.size() + " task left \n" + line);
                } else if (input.matches("todo(?: .+)?")) {
                    //store tasking
                    String words = splitGetWords(input);
                    if (words.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(words));
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks.get(tasks.size() - 1).toString() + "\n" +
                            "Now you have " + tasks.size() + " task left \n" + line);

                } else if(input.matches("delete \\d+")){
                    String task = tasks.get(splitGetNum(input)).toString();
                    if (task.isEmpty()){
                        throw new DukeException("Delete need number Delete <Number>.");
                    }
                    tasks.remove(splitGetNum(input));
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task: \n" +
                            "    " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                }
                else {
                    throw new DukeException("Sorry! I don't understand what you are saying." +
                            " Please enter a valid command. todo, deadline, event, list, mark/unmark, bye");
                }
            } catch (DukeException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
            }
        }

        System.out.println(line + "\n" + goodbye + "\n" + line);
    }
}