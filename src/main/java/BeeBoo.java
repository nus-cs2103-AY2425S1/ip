import java.util.Scanner;

public class BeeBoo {

    private void chatBox(String str) {
        //creating top horizontal lines
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        //printing chat output
        System.out.println(" " + str);
        System.out.println();
        //creating bottom horizontal lines
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Initialising BeeBoo and Scanner
        BeeBoo beeBoo = new BeeBoo();
        Scanner input = new Scanner(System.in);

        //getting user input
        beeBoo.chatBox("Hello! I'm BeeBoo\n What can i do for you?");
        String text = input.nextLine().toLowerCase();

        //Keeping chatbot open till bye is typed
        while (!text.equals("bye")) {
            beeBoo.chatBox(text);
            text = input.nextLine().toLowerCase();
        }
        beeBoo.chatBox("Bye. Hope to see you again soon!");
        input.close();
    }
}
