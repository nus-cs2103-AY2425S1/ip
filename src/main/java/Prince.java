import java.util.Scanner;
import java.util.ArrayList; // because i need to create a list of String items
import static java.lang.Integer.parseInt;


public class Prince {
    static ArrayList<Task> list = new ArrayList<>(); //static variable
    public static String conversation(String command) {
        if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";
        } else if(command.equals("list")) {
            return listDisplay(list);
        } else if(command.startsWith("mark") || command.startsWith("unmark")) {
            // used the library function .startsWith() to match the prefix to mark/unmark
            // use.split("") to split up the words
            // use.parseInt(num) to extract integer from the string

            // if mark, then extract integer and mark that task of the list as done
            // if unmark then extract integer and unmark that task of the list as not done

            String[] stringList = command.split(" ");
            int taskNum = Integer.parseInt(stringList[1]); //second word is the number
            Task t = list.get(taskNum - 1);

            if (taskNum < 1 || taskNum > list.size()) {
                return "Task number is out of range. Please retry.";
            }

            if(stringList[0].equals("mark")) {
                return t.markDone();
            } else {
                return t.markIncomplete();
            }

        } else {
            Task tsk = new Task(command);
            return addTask(tsk);
        }
    }

    public static String listDisplay(ArrayList<Task> list) {
        int length = list.size();
        // use String Builder to ensure that the string can be created on another line
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + list.get(i).printTask()).append("\n");
        }

        return sb.toString();
    }

    public static String addTask(Task task){
        // add task to the List
        // return a string
        list.add(task);
        return "added: " + task.getDescription();
    }

    public static void main(String[] args) {
        // if any words, repeat scanning, but the moment the word is bye,
        // then exit and print bye
        String line = "";
        System.out.println("Hello! I'm Prince!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like me to add to your TODO list today?");

        line = scanner.nextLine(); // what the user replied

        while(!line.equals("bye")) {
            System.out.println(conversation(line));
            System.out.println("How else would you like me to edit your TODO list today?");
            line = scanner.nextLine();
        }

        System.out.println(conversation(line));
    }
}

