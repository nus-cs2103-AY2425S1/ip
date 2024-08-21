import java.util.ArrayList;
import java.util.Scanner;

public class Bro {
    public static void main(String[] args) {
        String line = "______________________________________________________\n";
        System.out.println("   " + line + "   Hello! I'm Bro\n   What can I do for you?\n"
                           + "   " + line);
        ArrayList<Task> list = new ArrayList<>();
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            if (word.equalsIgnoreCase("list")) {
                int len = list.size();
                System.out.print("   " + line + "   Here are the tasks in your list:\n");
                for (int i = 0; i < len; i++) {
                    System.out.printf("   %d.%s\n", i + 1, list.get(i));
                }
                System.out.print("   " + line);
            } else if (word.length() > 5 &&
                       word.substring(0, 5).equalsIgnoreCase("mark ")) {
                int index = Integer.parseInt(word.substring(5));
                list.get(index - 1).mark();
                System.out.print("   " + line + "   Nice! I've marked this task as done:\n"
                                 + "   " + list.get(index - 1) + "\n   " + line);
            } else if (word.length() > 7 &&
                       word.substring(0, 7).equalsIgnoreCase("unmark ")) {
                int index = Integer.parseInt(word.substring(7));
                list.get(index - 1).unmark();
                System.out.print("   " + line + "   OK, I've marked this task as not done yet:\n"
                                 + "   " + list.get(index - 1) + "\n   " + line);
            } else if (word.length() >= 4 &&
                    word.substring(0, 4).equalsIgnoreCase("todo")) {
                if (word.length() == 4) {
                    System.out.printf("   %s\n   Please provide the name of the task!!!\n   %s",
                            line, line);
                } else {
                    Task curr = new Todo(word.substring(5));
                    list.add(curr);
                    System.out.printf("""
                           %s   Got it. I've added this task:
                           %s
                           \
                        Now you have %d tasks in the list
                           %s""", line, curr, list.size(), line);
                }
            } else if (word.length() >= 8 &&
                    word.substring(0, 8).equalsIgnoreCase("deadline")) {
                if (word.length() == 8) {
                    System.out.printf("   %s\n   Please provide the name of the task!!!\n   %s",
                            line, line);
                } else {
                    StringBuilder name = new StringBuilder();
                    int index = 9;
                    while (index < word.length() && word.charAt(index) != '/') {
                        name.append(word.charAt(index));
                        index++;
                    }
                    if (word.length() - index < 5 ||
                            !word.substring(index, index + 3).equalsIgnoreCase("/by")) {
                        System.out.printf("   %s\n   Please provide /by [date or time] after name\n   %s",
                                line, line);
                    } else {
                        Task curr = new Deadline(name.toString(), word.substring(index + 4));
                        list.add(curr);
                        System.out.printf("""
                           %s   Got it. I've added this task:
                           %s
                           \
                        Now you have %d tasks in the list
                           %s""", line, curr, list.size(), line);
                    }
                }
            } else if (word.length() >= 5 &&
                    word.substring(0, 5).equalsIgnoreCase("event")) {
                if (word.length() == 5) {
                    System.out.printf("   %s\n   Please provide the name of the task!!!\n   %s",
                            line, line);
                } else {
                    StringBuilder name = new StringBuilder();
                    int index = 6;
                    while (index < word.length() && word.charAt(index) != '/') {
                        name.append(word.charAt(index));
                        index++;
                    }
                    if (word.length() - index < 7 ||
                            !word.substring(index, index + 5).equalsIgnoreCase("/from")) {
                        System.out.printf("   %s\n   Please provide /from [date or time] /to [date or time] " +
                                "after name\n   %s", line, line);
                    } else {
                        index += 6;
                        StringBuilder from = new StringBuilder();
                        while (index < word.length() && word.charAt(index) != '/') {
                            from.append(word.charAt(index));
                            index++;
                        }
                        if (word.length() - index < 5 ||
                                !word.substring(index, index + 3).equalsIgnoreCase("/to")) {
                            System.out.printf("   %s\n   Please provide /to [date or time] " +
                                    "after name and start date or time\n   %s", line, line);
                        } else {
                            Task curr = new Event(name.toString(), from.toString(),
                                    word.substring(index + 4));
                            list.add(curr);
                            System.out.printf("""
                           %s   Got it. I've added this task:
                           %s
                           \
                        Now you have %d tasks in the list
                           %s""", line, curr, list.size(), line);
                        }
                    }
                }
            }
            else {
                System.out.println("   " + line + "   Well, what are u trying to do here? " +
                        "I don't quite understand :(\n" + "   " + line);
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        System.out.println("   " + line + "   Bye. Hope to see you again soon!\n" + "   " + line);
    }
}
