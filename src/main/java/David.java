import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class David {
    private Scanner sc;
    private String inputString;
    private String intro =
            "____________________________________________________________\n" +
            " Hello! I'm David\n" +
            " What can I do for you?\n" +
            "____________________________________________________________";
    private String outro =
            "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n";
    public List<String> tasks;

    //constructor for David
    public David() {
        this.sc = new Scanner(System.in);
        this.inputString = "";
        this.tasks = new ArrayList<>();
    };

    //run the chatbot
    public void activateChatBot() {
        System.out.println(this.intro);
        while(true) {
            inputString = sc.nextLine(); //get next input
            if (inputString.equals("bye")) {
                endChatBot();  //end chatbot
                break;
            } else if (inputString.equals("list")) {
                listTasks();
            } else {
                addToTask(inputString);
            }
        }
    }

    public void addToTask(String s) {
        tasks.add(s);
        System.out.println(
                "____________________________________________________________\n" +
                        "added: " + inputString + "\n" +
                        "____________________________________________________________\n");
    }

    public void listTasks() {
        for (int i = 0; i<tasks.size(); i++) {
            System.out.println(i+1 + ": " + tasks.get(i));
        }
    }

    public void endChatBot() {
        System.out.println(outro);
    }

    public static void main(String[] args) {
        David chat = new David();
        chat.activateChatBot();
    }
}
