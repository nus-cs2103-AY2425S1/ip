import java.util.Scanner;
public class Mentos
{
    private String[] tasks = new String[100];
    private int noTasks = 0;
    public static void main(String[] args) {
        Mentos mentos = new Mentos();
        mentos.startConversation();
        mentos.taskHandler();
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

    public void echoBack(){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")){
                return;
            }
            System.out.println("____________________________");
            System.out.println(input + "?");
            System.out.println("____________________________");
        }
    }

    public void taskHandler(){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")){
                return;
            } else if (input.equals("list")){
                displayTasks();
                continue;
            }
            System.out.println("____________________________");
            noTasks++;
            tasks[noTasks-1] = input;
            System.out.println("added: "+ input );
            System.out.println("____________________________");
        }
    }

    public void displayTasks(){
        for (int i = 0; i < noTasks; i++) {
            String task_out = String.format("%d. %s",i+1,tasks[i]);
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }

}

