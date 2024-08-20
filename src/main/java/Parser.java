import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private Storage store;
    private Ui ui;
    public Parser(Storage store, Ui ui) {
        this.store = store;
        this.ui = ui;

    }

    public void load() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "hoodini.txt");
        if(java.nio.file.Files.exists(path)) {
            try {
                readFromFile(path.toString());
            } catch (invalidTaskException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void handlefiletodo(String str) {
        if (str.startsWith("[X]")) {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            toDo.markdone();
            store.add(toDo);

        } else {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            store.add(toDo);

        }
    }


    private void handlefiledeadline(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0] + " ", str1.split("by: ")[1].replace(")","").trim());
            deadline.markdone();
            store.add(deadline);

        } else {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0] + " ", str1.split("by: ")[1].replace(")","").trim());
            store.add(deadline);

        }
    }

    private void handlefileevent(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ",2)[0] + " ", str1.split("from:")[1].split(" to: ")[0], str1.split("to:")[1].replace(")", ""));
            event.markdone();
            store.add(event);

        } else {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ",2)[0] + " ", str1.split("from:")[1].split(" to: ")[0], str1.split("to:")[1].replace(")", ""));
            store.add(event);

        }
    }

    public void readFromFile(String filepath) throws invalidTaskException {
        try {
            java.io.File file = new java.io.File(filepath);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("[T]")) {
                    handlefiletodo(str.substring(4));
                } else if (str.startsWith("[D]")) {
                    handlefiledeadline(str.substring(4));
                } else if (str.startsWith("[E]")) {
                    handlefileevent(str.substring(4));
                } else {
                    throw new invalidTaskException("Whoopsie! There are invalid tasks in the file");
                }
            }
        } catch (java.io.FileNotFoundException e) {
            ui.noFileFound();
            handleInput();
        }
    }


    public void end() {
        try {
            ui.showGoodbye();
            String home = System.getProperty("user.home");
            String filePath = home + "/Desktop/hoodini.txt";
            store.writeToFile(filePath);
        } catch (IOException e) {
            System.out.println("Whoopsie! There was an error writing to the file");
        }
    }

    public void handleInput() {
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
