package BMO;
public class Parser {

    public Parser() {
    }

    public String[] parse(String userInput) throws BMOException {
        String[] parsedInput = new String[5];
        String[] userInputArr = userInput.split(" ");
        String command = userInputArr[0];

        switch (command) {
        case "list":
            parsedInput[0] = "list";
            return parsedInput;

        case "mark":
            parsedInput[0] = "mark";
            if (userInputArr.length < 2) {
                throw new BMOException("\nOh no! Please specify the task number you want to mark as done!");
            }
            parsedInput[1] = userInputArr[1];
            return parsedInput;

        case "unmark":
            parsedInput[0] = "unmark";
            if (userInputArr.length < 2) {
                throw new BMOException("\nOh no! Please specify the task number you want to unmark!");
            }
            parsedInput[1] = userInputArr[1];
            return parsedInput;

        case "todo":
            parsedInput[0] = "todo";
            if (userInputArr.length < 2) {
                throw new BMOException("\nOh no! The description of a todo cannot be empty.");
            }
            parsedInput[1] = userInput.substring(5);
            return parsedInput;

        case "deadline":
            parsedInput[0] = "deadline";
            if (userInputArr.length < 2) {
                throw new BMOException("\nOh no! The description of a deadline cannot be empty.");
            }
            String[] deadlineDetails = userInput.substring(9).split(" /by ");
            if (deadlineDetails.length < 2) {
                throw new BMOException("\nOh no! You must specify a deadline for the task, using '/by [deadline]'.");
            }
            parsedInput[1] = deadlineDetails[0];
            parsedInput[2] = deadlineDetails[1];
            return parsedInput;

            case "event":
                parsedInput[0] = "event";
                if (userInputArr.length < 2) {
                    throw new BMOException("\nOh no! The description of an event cannot be empty.");
                }

                String[] eventDescription = userInput.substring(6).split(" /from ");
                if (eventDescription.length < 2) {
                    throw new BMOException("\nOh no! You must specify the start and end timings for the event, using '/from [start time] /to [end time]'.");
                }

                String[] eventTimings = eventDescription[1].split(" /to ");
                parsedInput[1] = eventDescription[0];
                parsedInput[2] = eventTimings[0];
                parsedInput[3] = eventTimings[1];
                return parsedInput;

            case "delete":
                parsedInput[0] = "delete";
                if (userInputArr.length < 2) {
                    throw new BMOException("\nOh no! Please specify the task number you want to delete! e.g 'delete 2'");
                }
                parsedInput[1] = userInputArr[1];
                return parsedInput;

            case "bye":
                parsedInput[0] = "bye";
                return parsedInput;
                
            default:
                parsedInput[0] = "invalid";
                return parsedInput;
        }
    }
}
