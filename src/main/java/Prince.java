import java.util.Scanner;
import java.util.ArrayList; // because i need to create a list of String items



public class Prince {
    static ArrayList<String> list = new ArrayList<>(); //static variable
    public static String conversation(String command) {
        if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";
        } else if(command.equals("list")) {
            return listDisplay(list);
        } else {
            return addTask(command);
        }
    }

    public static String listDisplay(ArrayList<String> list) {
        int length = list.size();
        // use String Builder to ensure that the string can be created on another line
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + list.get(i)).append("\n");
        }

        return sb.toString();
    }

    public static String addTask(String task){
        // add task to the List
        // return a string
        list.add(task);
        return "added: " + task;
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

