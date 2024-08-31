package bob;
import exceptions.CommandNotFoundException;
import exceptions.MissingParamsException;
import exceptions.PositionException;
import misc.Parser;
import misc.Storage;
import misc.Ui;
import task.Tasklist;


public class Bob {
    Storage storage;
    Tasklist tasklist;
    Ui ui;
    Parser parser;

    public Bob() {
        this.storage = new Storage("src/main/data/dataFile.txt");
        this.tasklist = new Tasklist();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public void run() throws CommandNotFoundException, MissingParamsException, PositionException {
        // init items
        boolean exit = false;
        String[] strArr = new String[2];

        // initial message
        ui.startGame();


        // main loop
        while(!exit) {
            strArr = parser.requestInput();
            ui.retBlank();
            try {
                // main command control flow
                switch (strArr[0].toLowerCase()) {
                case "bye":
                    exit = true;
                    ui.endGame();
                    break;

                case "list":
                    ui.replyGetList(tasklist);
                    break;

                case "mark":
                    tasklist = ui.replyMarkDone(Integer.parseInt(strArr[1]) - 1, tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "unmark":
                    tasklist = ui.replyMarkUndone(Integer.parseInt(strArr[1]) - 1, tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "todo":
                    ui.replyTodo(strArr[1], tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "event":
                    ui.replyEvent(strArr[1], tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "deadline":
                    ui.replyDeadline(strArr[1], tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "delete":
                    ui.replyDelete(Integer.parseInt(strArr[1]) - 1, tasklist);
                    storage.updateDataFile(tasklist.getList());
                    break;

                case "find":
                    ui.replyFind(strArr[1], tasklist);
                    break;
                    
                default:
                    throw new CommandNotFoundException();
                }
            } catch (CommandNotFoundException | MissingParamsException | PositionException c) {
                System.out.println(c + "\n" + ui.retBlank());
            }
        }   
    }

    public static void main(String[] args) throws CommandNotFoundException, 
        MissingParamsException, PositionException {
        new Bob().run();
    }
}
