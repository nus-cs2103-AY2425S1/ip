import java.util.Scanner;

public class Hoodini {

    public Hoodini() {
        start();

    }

    public void start() {
        System.out.println("Hello I am Hoodini! How may I assist you?");
        handleInput();

    }

    public void end() {
        System.out.println("Bye! Come back to Hoodini soon!");
    }

    private void handleInput() {
        Scanner sc = new Scanner(System.in);
        Storage store = new Storage();
        while(true) {
            String str = sc.nextLine();
            if(str.equalsIgnoreCase("bye")) {
                end();
                break;
            } else if(str.isEmpty()) {
                store.empty();
            } else if(str.equalsIgnoreCase("list")) {
                store.output();
            } else if(str.startsWith("todo")) {
                if(str.endsWith("todo")) {
                    System.out.println("Whoopsie! Please enter a task");
                } else {
                    ToDo toDo = new ToDo(str);
                    store.store(toDo);
                }
            } else if(str.startsWith("deadline")) {
                if(str.endsWith("deadline")) {
                    System.out.println("Whoopsie! Please enter a task");
                } else {
                    Deadline deadline = new Deadline(str);
                    store.store(deadline);
                }
            } else if(str.startsWith("event")) {
                if(str.endsWith("event")) {
                    System.out.println("Whoopsie! Please enter a task");
                } else {
                    Event event = new Event(str);
                    store.store(event);
                }
            } else if(str.startsWith("mark")) {
                store.mark(str);
            } else if(str.startsWith("unmark")) {
                store.unmark(str);
            } else {
                System.out.println("Whoopsie! I am unable to understand your request!");
            }

        }

    }



}
