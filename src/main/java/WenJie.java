import java.util.Objects;
import java.util.Scanner;

public class WenJie {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        Task[] taskList = new Task[100];
        int currentPointer = 0;

        String greeting =
                "____________________________________________________________\n" +
                "Eh wasusp la bro, my name is Wen Jie.\n" +
                "What you want?\n" +
                "____________________________________________________________\n";

        System.out.println(greeting);

        while(active) {
            String input = scanner.nextLine();

            String[] parts = input.split(" ");

            String firstWord = parts[0];

            switch(firstWord) {
                case "bye": {
                    active = false;
                    String farewell =
                            "____________________________________________________________\n" +
                            "  Paiseh bro I zao liao, see you around ah bro.\n" +
                            "____________________________________________________________\n";
                    System.out.println(farewell);
                    break;
                }

                case "list": {
                    String list =
                            "____________________________________________________________\n" +
                            displayList(taskList) +
                            "____________________________________________________________\n";
                    System.out.println(list);
                    break;
                }

                case "mark": {
                    int taskNo = Integer.parseInt(parts[1]) - 1;
                    taskList[taskNo].setStatusIcon(true);
                    String output =
                            "____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            taskList[taskNo] + "\n" +
                            "____________________________________________________________";
                    System.out.println(output);
                    break;
                }

                case "unmark": {
                    int taskNo = Integer.parseInt(parts[1]) - 1;
                    taskList[taskNo].setStatusIcon(false);
                    String output =
                            " ____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet:\n " +
                            taskList[taskNo] + "\n" +
                            " ____________________________________________________________";
                    System.out.println(output);
                    break;
                }

                case "todo": {
                    ToDo temp = new ToDo(input.substring(5));

                    String output =
                            "____________________________________________________________\n" +
                            "Got it. I've added this task:\n" +
                            temp + "\n" +
                            "Now you have " + (currentPointer + 1)+" tasks in the list.\n" +
                            "____________________________________________________________\n";
                    System.out.println(output);
                    taskList[currentPointer] = temp;
                    currentPointer++;
                    break;
                }

                case "deadline": {
                    String by = "";
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].charAt(0) == '/') {
                            for (int j = i + 1; j < parts.length; j++){
                                by += parts[j] + " ";
                            }
                            break;
                        }
                    }
                    int endIndex = 0;
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) == '/') {
                            endIndex = i;
                            break;
                        }
                    }

                    String desc = input.substring(9, endIndex);
                    Deadline temp = new Deadline(desc, by);
                    taskList[currentPointer] = temp;
                    String output =
                            "____________________________________________________________\n" +
                            "Got it. I've added this task:\n" +
                            temp + "\n" +
                            "Now you have " + (currentPointer + 1)+" tasks in the list.\n" +
                            "____________________________________________________________\n";
                    System.out.println(output);
                    currentPointer++;
                    break;
                }

                case "event": {
                    String from = "", to = "";

                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].charAt(0) == '/') {
                            int j = i + 1;
                            while(parts[j].charAt(0) != '/') {
                                from += parts[j] + " ";
                                j++;
                            }
                            j++;
                            while(j < parts.length) {
                                to += parts[j];
                                j++;
                            }

                            break;
                        }
                    }
                    int endIndex = 0;
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) == '/') {
                            endIndex = i;
                            break;
                        }
                    }

                    String desc = input.substring(6, endIndex);
                    Event temp = new Event(desc, from, to);
                    taskList[currentPointer] = temp;
                    String output =
                            "____________________________________________________________\n" +
                            "Got it. I've added this task:\n" +
                            temp + "\n" +
                            "Now you have " + (currentPointer + 1)+" tasks in the list.\n" +
                            "____________________________________________________________\n";
                    System.out.println(output);
                    currentPointer++;
                    break;
                }


                default :
                    String output =
                            "____________________________________________________________\n" +
                             "added: " + input + "\n" +
                            "____________________________________________________________\n";
                    System.out.println(output);
                    Task temp = new Task(input);
                    taskList[currentPointer] = temp;
                    currentPointer++;
                    break;
            }
        }

    }

    public static String displayList(Task[] list) {
        String result = "";
        int i = 0;
        while (list[i] != null){
            String newLine = (i + 1) + ". " + list[i] + "\n";
            result += newLine;
            i++;
        }
        return result;
    }


}
