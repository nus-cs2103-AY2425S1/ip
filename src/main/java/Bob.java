import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bob {

    private final TaskList myTasks;
    private final String filePath;

    public Bob(String filePath) {
        this.myTasks = new TaskList();
        this.filePath = filePath;
    }

    private void initialize() {
        Storage.readData(this.myTasks, this.filePath);
    }

    public static void main(String[] args) {
        Bob myBot = new Bob("./userdata.txt");
        myBot.initialize();
        myBot.startChatBot();
    }

    public void startChatBot() {
        UI.printGreetings();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userCommand = UI.readCommand();
                UI.printLine();
                Command command = Parser.parseCommand(userCommand, myTasks.size());
                command.execute(myTasks);
                isRunning = command.isRunning();
                Storage.writeData(myTasks, this.filePath);
            } catch (EmptyArgumentException | MissingArgumentException |
                     InvalidTaskNumberException e) {
                UI.printMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                UI.printMessage("Invalid date/time has been entered. Please key in with a DD/MM/YYYY format or DD/MM/YYYY HHMM format");
            } catch (InvalidInputException | InputMismatchException e) {
                UI.printMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                UI.printLine();
            }

        }
    }

}

