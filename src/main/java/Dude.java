import java.util.Scanner;

public class Dude {
    private Scanner scanner;
    private String input;
    private String line = "____________________________________________________________";
    private String botName = "Dude";
    private String[] tasks;
    private int taskPointer;

    public Dude(){
        this.scanner = new Scanner(System.in);
        input = "";
        tasks = new String[100];
        taskPointer = 0;
    }

    public void start(){
        System.out.println(line);
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println(line);

        readAndReact();
    }

    public void readAndReact(){
        input = scanner.nextLine();

        if(input.equals("bye")){
            exit();
            return;
        }
        else if(input.equals("list")){
            list();
        }
        else{
            add(input);
        }

        readAndReact();
    }

    public void add(String task){
        this.tasks[taskPointer] = task;
        taskPointer++;

        System.out.println(line);
        System.out.println("added: " + task);
        System.out.println(line);
    }

    public void list(){
        System.out.println(line);

        for(int i = 1; i <= taskPointer; i ++){
            System.out.println(i + ". " + tasks[i - 1]);
        }

        System.out.println(line);
    }

    public void exit(){
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();
    }

    public static void main(String[] args) {
        Dude dude = new Dude();
        dude.start();
    }
}
