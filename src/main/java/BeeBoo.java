
public class BeeBoo {

    protected void chatBox(String str) {
        //creating top horizontal line
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        //printing out string
        System.out.println(" " + str);
        //creating bottom horizontal line
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        //Initialising chatbot
        BeeBoo beeBoo = new BeeBoo();
        beeBoo.chatBox("Hello! I'm BeeBoo\n What can i do for you?");
        beeBoo.chatBox("Bye. Hope to see you soon!");
    }
}
