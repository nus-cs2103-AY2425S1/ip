import java.util.Scanner;
public class Mentos
{
    private String MARKED = "mark";
    private String UNMARKED = "unmark";
    private Task[] tasks = new Task[100];
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
            else if (input.startsWith(MARKED)){
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks[index-1].markAsDone();
                System.out.println("Nicely done! This task is marked as done!");
                System.out.println(tasks[index-1].toString());
                System.out.println("____________________________");
                continue;
            } else if (input.startsWith(UNMARKED)){
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks[index-1].markAsNotDone();
                System.out.println("Holdup this task is not done!");
                System.out.println(tasks[index-1].toString());
                System.out.println("____________________________");
                continue;
            }
            System.out.println("____________________________");
            noTasks++;
            tasks[noTasks-1] = new Task(input);
            System.out.println("added: "+ input );
            System.out.println("____________________________");
        }
    }

    public void displayTasks(){
        for (int i = 0; i < noTasks; i++) {
            String task_out = String.format("%d. %s",i+1,tasks[i].toString());
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }

}

