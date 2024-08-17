
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


    //constructor for David
    public David() {
        sc = new Scanner(System.in);
        inputString = "";
    };

    //run the chatbot
    public void activateChatBot() {
        System.out.println(this.intro);
        while(true) {
            inputString = sc.nextLine(); //get next input
            if (inputString.equals("bye")) {
                endChatBot();  //end chat bot
                break;
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                        inputString + "\n" +
                        "____________________________________________________________\n");
            }
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
