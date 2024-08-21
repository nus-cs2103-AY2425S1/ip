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
            } else if (word.length() > 5 &&
                    word.substring(0, 5).equalsIgnoreCase("todo ")) {
                Task curr = new Todo(word.substring(5));
                list.add(curr);
                System.out.printf("""
                           %s   Got it. I've added this task:
                           %s
                           \
                        Now you have %d tasks in the list
                           %s""", line, curr, list.size(), line);
            } else if (word.length() > 9 &&
                    word.substring(0, 9).equalsIgnoreCase("deadline ")) {
                StringBuilder name = new StringBuilder();
                int index = 9;
                while (word.charAt(index) != '/') {
                    name.append(word.charAt(index));
                    index++;
                }
                Task curr = new Deadline(name.toString(), word.substring(index + 4));
                list.add(curr);
                System.out.printf("""
                           %s   Got it. I've added this task:
                           %s
                           \
                        Now you have %d tasks in the list
                           %s""", line, curr, list.size(), line);
            } else if (word.length() > 6 &&
                    word.substring(0, 6).equalsIgnoreCase("event ")) {
                StringBuilder name = new StringBuilder();
                int index = 6;
                while (word.charAt(index) != '/') {
                    name.append(word.charAt(index));
                    index++;
                }
                index += 6;
                StringBuilder from = new StringBuilder();
                while (word.charAt(index) != '/') {
                    from.append(word.charAt(index));
                    index++;
                }
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
            else {
                list.add(new Task(word));
                System.out.println("   " + line + "   added: " + word + "\n" + "   " + line);
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        System.out.println("   " + line + "   Bye. Hope to see you again soon!\n" + "   " + line);
    }
}
