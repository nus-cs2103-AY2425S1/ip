import java.util.ArrayList;
import java.util.Scanner;

public class BeeBoo {

    //Arraylist for the tasklist
    private ArrayList<String> list;

    //Initialise tasklist when beeboo is created
    public BeeBoo() {
        list = new ArrayList<>();
    }

    //Adding to the tasklist
    private String addList(String str) {
        list.add(str);
        return "added: " + str;
    }

    //Returning list when prompted
    private String produceList() {
        String result = "";
        for (String str : list) {
            result = result + (list.indexOf(str) + 1) + ". " + str + "\n";
        }
        return result;
    }

    //Creating chatbox
    private void chatBox(String str) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(str);
        System.out.println();
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Initialise the chatbot and scanner
        BeeBoo beeBoo = new BeeBoo();
        Scanner input = new Scanner(System.in);

        //Getting user input
        beeBoo.chatBox("Hello! I'm BeeBoo\nWhat can i do for you?");
        String text = input.nextLine().toLowerCase();

        //add user input till user types bye
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                //produces list when user types list
                beeBoo.chatBox(beeBoo.produceList());
            } else {
                //else add items to list
                beeBoo.chatBox(beeBoo.addList(text));
            }
            text = input.nextLine().toLowerCase();
        }
        beeBoo.chatBox("Bye. Hope to see you again soon!");
        input.close();
    }
}
