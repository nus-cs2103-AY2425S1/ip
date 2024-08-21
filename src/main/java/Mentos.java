public class Mentos
{
    public static void main(String[] args) {
        Mentos mentos = new Mentos();
        mentos.startConversation();
        mentos.endConversation();
    }

    public void startConversation(){
        System.out.println("____________________________");
        System.out.println("Hello! I'm Mentos\nWhat can I do to help you?");
        System.out.println("____________________________");
    }

    public void endConversation(){
        System.out.println("____________________________");
        System.out.println("Pop a Mentos, stay fresh! See you next time!");
        System.out.println("____________________________");
    }
}

