import java.util.Scanner;

public class Hoodini {

    public Hoodini() {
        start();

    }

    public void start() {
        System.out.println("Hello I am Hoodini!, how may I assist you?");
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
                ToDo toDo = new ToDo(str);
                store.store(toDo);
            } else if(str.startsWith("deadline")) {
                Deadline deadline = new Deadline(str);
                store.store(deadline);
            } else if(str.startsWith("event")) {
                Event event = new Event(str);
                store.store(event);
            } else if(str.startsWith("mark")) {
                store.mark(str);
            } else if(str.startsWith("unmark")) {
                store.unmark(str);
            } else {
                Input input = new Input(str);
                store.store(input);
            }

        }

    }



}
