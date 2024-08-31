import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Gumball {

    public String input;
    public Scanner inputScanner;
    public TaskList list;
    private FileManager fileManager;

    private UI ui;
    public static void main(String[] args) {
        try {
            Gumball chat = new Gumball();
            chat.start();
        } catch (IOException e) {
            UI.print(e.getMessage());
        } catch (InputErrorException e) {
            UI.print("Error in file please start over.");
        }

    }


    public Gumball() throws IOException, InputErrorException {
        ui = new UI();
        inputScanner = new Scanner(System.in);
        fileManager = new FileManager("./gumball.txt");
        list = fileManager.loadFile();
    }

    public void start() throws FileNotFoundException {
        ui.intro();
        while (true){
            input = ui.readCommand();
            if(input.equals("bye")) break;
            try {
                Command c = Parser.parse(input);
                c.execute(list, ui, fileManager);
            } catch (InputErrorException e) {
                ui.print(e.getMessage());
            } catch (IOException e) {
                ui.print(e.getMessage());
            }
        }
        ui.outro();
    }

   /* public void execute(String command) throws InputErrorException, IOException {
        if (command.equals("bye")) {

        } else if (command.equals("list")) {
            getList();
        } else if (command.startsWith("mark ")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            list.mark(num);
            print("Nice! I've marked this task as done:\n" + list.getSpecific(num));
        } else if (command.startsWith("delete ")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            String temp = list.delete(num);
            print("Nice! I've deleted this task:\n" + temp
                    + String.format("\nNow you have %d tasks in the list.",list.getN()));
            fileManager.updateFile(list);
        } else if (command.startsWith("todo")) {
            addToList(new ToDos(command));
            fileManager.updateFile(list);
        } else if (command.startsWith("deadline")) {
            addToList(new Deadlines(command));
            fileManager.updateFile(list);
        } else if (command.startsWith("event")) {
            addToList(new Events(command));
            fileManager.updateFile(list);
        } else {
            throw(new InputErrorException("Sorry I don't know how to do that"));
        }
    }

    public void addToList(Task task) throws InputErrorException {
        print("Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.",list.getN()));
    }

    public void getList() {
        print(list.get());
    }


    public void intro() {
        String str = "Hello! I'm Gumball \n"
                + "What can I do for you?";
        print(str);
    }

    public void outro() {
        String str ="Bye. Hope to see you again soon!";
        print(str);
    }

    public static void print(String out) {
        String str ="____________________________________________________________";
        System.out.println(str);
        System.out.println(out);
        System.out.println(str);

    }*/
}
