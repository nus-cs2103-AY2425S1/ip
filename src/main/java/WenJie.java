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
