package hoodini;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the user inputs and file inputs.
 * Ensures that the inputs are valid
 */
public class Parser {

    private Storage store;
    private Ui ui;
    public Parser(Storage store, Ui ui) {
        this.store = store;
        this.ui = ui;

    }

    /**
     * Method to scan for file on desktop and load the file
     */
    public void load() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "hoodini.txt");
        if(java.nio.file.Files.exists(path)) {
            try {
                readFromFile(path.toString());
            } catch (InvalidTaskException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void handleFileToDo(String str) {
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


    private void handleFileDeadline(String str) {
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

    private void handleFileEvent(String str) {
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

    /**
     * Method to read from file and input content
     * to the chatbot
     * @param filepath the path of the file
     * @throws InvalidTaskException Handles invalid tasks in file
     */

    public void readFromFile(String filepath) throws InvalidTaskException {
        try {
            java.io.File file = new java.io.File(filepath);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("[T]")) {
                    handleFileToDo(str.substring(4));
                } else if (str.startsWith("[D]")) {
                    handleFileDeadline(str.substring(4));
                } else if (str.startsWith("[E]")) {
                    handleFileEvent(str.substring(4));
                } else {
                    throw new InvalidTaskException("Whoopsie! There are invalid tasks in the file");
                }
            }
        } catch (java.io.FileNotFoundException e) {
            ui.noFileFound();
            handleInput();
        }
    }


    /**
     * Method to end the chatbot
     */
    private void end() {
        try {
            ui.showGoodbye();
            String home = System.getProperty("user.home");
            String filePath = home + "/Desktop/hoodini.txt";
            store.writeToFile(filePath);
        } catch (IOException e) {
            System.out.println("Whoopsie! There was an error writing to the file");
        }
    }

    /**
     * Handles the user inputs and identifies tasks to store
     * into the task list
     */
    public void handleInput() {
        Scanner sc = new Scanner(System.in);

        try{
            while(true) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    end();
                    break;
                } else if (str.isEmpty()) {
                    ui.empty();
                } else if (str.equalsIgnoreCase("list")) {
                    store.output();
                } else if (str.startsWith("todo")) {
                    handleToDo(str);
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
                } else if (str.startsWith("find")) {
                    store.find(str);
                } else {
                    throw new InvalidInputException("Whoopsie! I am unable to understand your request!");
                }
            }
        } catch (InvalidTaskException | InvalidInputException e) {
            System.out.println(e.getMessage());


        }
        sc.close();


    }

    private void handleToDo(String str) throws InvalidTaskException {
        if (str.trim().equalsIgnoreCase("todo")) {
            throw new InvalidTaskException("Whoopsie! Please enter a task");
        } else {
            ToDo toDo = new ToDo(str);
            store.store(toDo);
        }
    }

    private void handleDeadline(String str) throws InvalidTaskException {
        if (str.trim().equalsIgnoreCase("deadline")) {
            throw new InvalidTaskException("Whoopsie! Please enter a task");
        } else {
            Deadline deadline = new Deadline(str);
            store.store(deadline);
        }
    }

    private void handleEvent(String str) throws InvalidTaskException {
        if (str.trim().equalsIgnoreCase("event")) {
            throw new InvalidTaskException("Whoopsie! Please enter a task");
        } else {
            Event event = new Event(str);
            store.store(event);
        }
    }
}
