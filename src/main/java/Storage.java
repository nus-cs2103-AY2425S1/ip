import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;


public class Storage {
    private ArrayList<Input> input;
    private static int counter = 0;

    public Storage() {
        this.input = new ArrayList<>();
    }

    public void store(Input input) {
        if(input.empty()) {
            System.out.println("Whoopsie! Please enter a task");
        } else {
            this.input.add(input);
            counter++;
            System.out.println("Noted. I have added this task:");
            System.out.println(input);
            System.out.println("You have " + counter + " tasks in the list.");
        }


    }

    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Input i : input) {
                writer.write(i.toString());
                writer.newLine();
            }
        }
    }

    private void handletodo(String str) {
        if (str.startsWith("[X]")) {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            toDo.markdone();
            input.add(toDo);
            counter++;
        } else {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            input.add(toDo);
            counter++;
        }
    }







    private void handledeadline(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0] + " ", str1.split("by: ")[1].replace(")",""));
            deadline.markdone();
            input.add(deadline);
            counter++;
        } else {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0], str1.split("by: ")[1].replace(")",""));
            input.add(deadline);
            counter++;
        }
    }

    private void handleevent(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ",2)[0] + " ", str1.split("from:")[1].split(" to: ")[0], str1.split("to:")[1].replace(")", ""));
            event.markdone();
            input.add(event);
            counter++;
        } else {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ",2)[0] + " ", str1.split("from:")[1].split(" to: ")[0], str1.split("to:")[1].replace(")", ""));
            input.add(event);
            counter++;
        }
    }

    public void readFromFile(String filepath) throws invalidTaskException {
        try {
            java.io.File file = new java.io.File(filepath);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("[T]")) {
                    handletodo(str.substring(4));
                } else if (str.startsWith("[D]")) {
                    handledeadline(str.substring(4));
                } else if (str.startsWith("[E]")) {
                    handleevent(str.substring(4));
                } else {
                    throw new invalidTaskException("Whoopsie! There are invalid tasks in the file");
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void output() {
        System.out.println("Here are the list of tasks that needs to be completed: ");
        for(int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input.get(i));
        }
    }

    public void mark(String str) {
        int i = Integer.parseInt(str.substring(5));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input.get(i-1).done();

        }


    }

    public void delete(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to delete");
        } else {
            System.out.println("Noted. I have deleted this task:");
            System.out.println(input.get(i-1));
            input.remove(i-1);
            counter--;
            System.out.println("You have " + counter + " tasks in the list.");
        }
    }

    public void empty() {

        System.out.println("Whoopsie! Please enter a task");
    }

    public void unmark(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input.get(i-1).undone();

        }

    }

}
