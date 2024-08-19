import java.io.IOException;
import java.util.Scanner;


public class Hoodini {
    private Storage store;

    public Hoodini() {
        this.store = new Storage();
        start();


    }

    public void start() {
        System.out.println("Hello I am Hoodini! How may I assist you?");
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "hoodini.txt");
        if(java.nio.file.Files.exists(path)) {
            try {
                store.readFromFile(path.toString());
            } catch (invalidTaskException e) {
                System.out.println(e.getMessage());
            }

        }

        handleInput();

    }

    public void end() {
        try {
            System.out.println("Bye! Come back to Hoodini soon!");
            String home = System.getProperty("user.home");
            String filePath = home + "/Desktop/hoodini.txt";
            store.writeToFile(filePath);
        } catch (IOException e) {
            System.out.println("Whoopsie! There was an error writing to the file");
        }
    }

    private void handleInput() {
        Scanner sc = new Scanner(System.in);

        try{
            while(true) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    end();
                    break;
                } else if (str.isEmpty()) {
                    store.empty();
                } else if (str.equalsIgnoreCase("list")) {
                    store.output();
                } else if (str.startsWith("todo")) {
                    handletodo(str);
                } else if (str.startsWith("deadline")) {
                    handleDeadline(str);
                } else if (str.startsWith("event")) {
                    handleEvent(str);
                } else if (str.startsWith("mark")) {
                    store.mark(str);
                } else if (str.startsWith("unmark")) {
                    store.unmark(str);
                } else if (str.startsWith("delete")) {
                    store.delete(str);
                } else {
                    throw new invalidInputException("Whoopsie! I am unable to understand your request!");
                }
            }
        } catch (invalidTaskException | invalidInputException e) {
            System.out.println(e.getMessage());


        }
        sc.close();


    }

    private void handletodo(String str) throws invalidTaskException {
        if (str.trim().equalsIgnoreCase("todo")) {
            throw new invalidTaskException("Whoopsie! Please enter a task");
        } else {
            ToDo toDo = new ToDo(str);
            store.store(toDo);
        }
    }

    private void handleDeadline(String str) throws invalidTaskException {
        if (str.trim().equalsIgnoreCase("deadline")) {
            throw new invalidTaskException("Whoopsie! Please enter a task");
        } else {
            Deadline deadline = new Deadline(str);
            store.store(deadline);
        }
    }

    private void handleEvent(String str) throws invalidTaskException {
        if (str.trim().equalsIgnoreCase("event")) {
            throw new invalidTaskException("Whoopsie! Please enter a task");
        } else {
            Event event = new Event(str);
            store.store(event);
        }
    }



}
