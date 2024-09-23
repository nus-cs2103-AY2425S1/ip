
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Chappy {

    private static enum Command {
        BYE("bye"), LIST("list"), UNMARK("unmark"), MARK("mark"), TODO("todo"), DEADLINE("deadline"), EVENT("event"),
        DELETE("delete");

        private final String keyword;

        Command(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

    }

    protected static ArrayList<Task> userInputArray = new ArrayList<Task>();

    private static final String DATA_FILE = "." + File.separator + "data" + File.separator + "task_list.json";

    public static void main(String[] args) throws CreateTaskException, IOException, ParseException {
        try {
            Chappy.loadFromDisk();
        } catch (IOException | ParseException e) {
            System.out.println("oh SIR! There was an error loading data from the saved file!");
            System.out.println(e.getMessage());
            return;
        }

        String logo = """
                            :..
                           #@@@@#+:
                          -@@@@@@@@@*-
                         .@@@@@@@@@@@@@#-
                         %@@@@@@@@@@@@@@@%=
                  -==:. *#@@@@@@@@@@@@@@@@@@.
                 .@@@@@%=--*@@@@@@@@%%%%@@%-
                  .#@@@#-::::=#@@@@%%%@@%-
                    :#@@@#-::...:+#%@@%=
                      .+%@@%*-     :*=
                         :+%@@@*=-=%%=
                            :=#@@@@@@@%.
                                :=*#@@@*
                 .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.
                | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
                | |     ______   | || |  ____  ____  | || |      __      | || |   ______     | || |   ______     | || |  ____  ____  | |
                | |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || |  |_   __ \\   | || |  |_   __ \\   | || | |_  _||_  _| | |
                | |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || |    | |__) |  | || |    | |__) |  | || |   \\ \\  / /   | |
                | |  | |         | || |   |  __  |   | || |   / ____ \\   | || |    |  ___/   | || |    |  ___/   | || |    \\ \\/ /    | |
                | |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |   _| |_      | || |   _| |_      | || |    _|  |_    | |
                | |   `._____.'  | || | |____||____| | || ||____|  |____|| || |  |_____|     | || |  |_____|     | || |   |______|   | |
                | |              | || |              | || |              | || |              | || |              | || |              | |
                | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
                 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
                 """;
        System.out.println("Good day sir! \n" + logo + "\nat your service!");
        System.out.println("I shall await your next request.");
        Scanner scannerObj = new Scanner(System.in);
        String userInput;
        while (scannerObj.hasNext()) {
            userInput = scannerObj.nextLine().trim();
            if (userInput.equals("")) {
                continue;
            }
            System.out.println("____________________________________________________________");
            Command userInputCommand = null;
            for (Command command : Command.values()) {
                if (userInput.toLowerCase().contains(command.getKeyword())) {
                    userInputCommand = command;
                    break;
                }
            }
            try {
                if (!Objects.isNull(userInputCommand)) {

                    switch (userInputCommand) {
                        case BYE:
                            System.out.println("It's been a pleasure serving you! Farewell sir.");
                            return;

                        case LIST:
                            System.out.println("As requested, here are your outstanding tasks sir:");
                            for (int i = 0; i < Chappy.userInputArray.size(); i++) {
                                System.out.println(i + 1 + "." + userInputArray.get(i).toString());
                            }
                            break;

                        case UNMARK:
                            String[] unmarkInput = userInput.trim().split("(?i)" + Command.UNMARK.getKeyword());
                            if (unmarkInput.length < 2) {
                                throw new CreateTaskException(
                                        "Oh SIR! The index input of a Unmark command cannot be empty!");
                            }
                            int unmarkIndex = Integer.parseInt(unmarkInput[1].trim()) - 1;
                            if (unmarkIndex < 0 || unmarkIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            userInputArray.get(unmarkIndex).markAsNotDone();
                            Chappy.saveToDisk();
                            break;

                        case MARK:
                            String[] markInput = userInput.trim().split("(?i)" + Command.MARK.getKeyword());
                            if (markInput.length < 2) {
                                throw new CreateTaskException(
                                        "Oh SIR! The index input of a Mark command cannot be empty!");
                            }
                            int markIndex = Integer.parseInt(markInput[1].trim()) - 1;
                            if (markIndex < 0 || markIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            userInputArray.get(markIndex).markAsDone();
                            Chappy.saveToDisk();
                            break;

                        case TODO:
                            String[] todoInput = userInput.trim().split("(?i)" + Command.TODO.getKeyword());
                            if (todoInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The description of a Todo cannot be empty!");
                            }
                            Todo todo = new Todo(todoInput[1].trim());
                            Task.addTask(todo);
                            Chappy.saveToDisk();
                            break;

                        case DEADLINE:
                            Deadline deadline = Deadline.of(userInput);
                            if (deadline != null) {
                                Task.addTask(deadline);
                                Chappy.saveToDisk();
                            }
                            break;

                        case EVENT:
                            Event event = Event.of(userInput);
                            if (event != null) {
                                Task.addTask(event);
                                Chappy.saveToDisk();
                            }
                            
                            break;

                        case DELETE:
                            String[] deleteInput = userInput.trim().split("(?i)" + Command.DELETE.getKeyword());

                            if (deleteInput.length < 2) {
                                throw new CreateTaskException(
                                        "Oh SIR! The index input of a Delete command cannot be empty!");
                            }
                            int deleteIndex = Integer.parseInt(deleteInput[1].trim()) - 1;
                            if (deleteIndex < 0 || deleteIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            Task.removeTask(deleteIndex);
                            Chappy.saveToDisk();
                            break;
                    }
                } else {
                    throw new CreateTaskException("Oh SIR! I can't understand what you are saying!");
                }

            } catch (CreateTaskException e) {
                System.out.println(e.getMessage());

            }

        }
    }

    public static void saveToDisk() throws IOException {
        try {
            JSONArray jsonArray = new JSONArray();
            FileWriter file = new FileWriter(DATA_FILE);
            for (int i = 0; i < userInputArray.size(); i++) {
                jsonArray.add(userInputArray.get(i).toJson());
            }
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            System.out.println("oh SIR! There was an error saving data to the saved file!");
            System.out.println(e.getMessage());
        }
    }

    public static void loadFromDisk() throws IOException, ParseException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            if (file.length() > 0) {
                JSONParser jsonParser = new JSONParser();

                FileReader fileReader = new FileReader(DATA_FILE);
                JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject o = (JSONObject) jsonArray.get(i);
                        Task t;
                        if (o.get("type").equals(Command.TODO.getKeyword())) {
                            t = Todo.fromJson(o);
                            userInputArray.add(t);
                        } else if (o.get("type").equals(Command.DEADLINE.getKeyword())) {
                            t = Deadline.fromJson(o);
                            userInputArray.add(t);
                        } else if (o.get("type").equals(Command.EVENT.getKeyword())) {
                            t = Event.fromJson(o);
                            userInputArray.add(t);
                        }

                    }
                }
            }

        }

    }
}