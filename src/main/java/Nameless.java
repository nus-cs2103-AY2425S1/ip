import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Nameless {
    private static final String line = "_____________________________________________________________";
    private static final String name = "Nameless";
    private static final String greetings = "Hello, I'm " + name + "\n" + "What can I do for you?";
    private static final String goodbye = "Bye. Hope to see you again!";
    private static final Task[] tasks = new Task[100];
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
        int counter = 0;

        String temp;

        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    //list tasking
                    System.out.println(line + "\n Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        //print out all tasking
                        System.out.println("    " + (i + 1) + "." + tasks[i].toString());
                    }
                    System.out.println(line);
                } else if (input.matches("mark \\d+")) {
                    System.out.println(line);
                    tasks[splitGetNum(input)].markTask();
                    System.out.println(line);
                } else if (input.matches("unmark \\d+")) {
                    System.out.println(line);
                    tasks[splitGetNum(input)].unMarkTask();
                    System.out.println(line);
                } else if (input.matches("deadline(?: .+)?")) {
                    temp = splitGetWords(input);
                    if (temp.length() != 2) {
                        throw new DukeException("incorrect format use 'deadline <task> /by <date>'");
                    }
                    String[] words = temp.split(" /by ");
                    tasks[counter] = new Deadline(words[0], words[1]);
                    counter++;
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks[counter - 1].toString() + "\n" +
                            "Now you have " + counter + " task left \n" + line);
                } else if (input.matches("event(?: .+)?")) {
                    temp = splitGetWords(input);
                    if (temp.length() != 3) {
                        throw new DukeException("incorrect format use 'event <task> /from <date> /to <date>'");
                    }
                    String[] words = temp.split(" /from | /to ");
                    tasks[counter] = new Event(words[0], words[1], words[2]);
                    counter++;
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks[counter - 1].toString() + "\n" +
                            "Now you have " + counter + " task left \n" + line);
                } else if (input.matches("todo(?: .+)?")) {
                    //store tasking
                    String words = splitGetWords(input);
                    if (words.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks[counter] = new Todo(words);
                    counter++;
                    System.out.println(line + "\n" + "Got it. I've added this task:" +
                            "\n     " + tasks[counter - 1].toString() + "\n" +
                            "Now you have " + counter + " task left \n" + line);

                } else {
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

