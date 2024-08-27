public class Parser {
    
    public static String[] parseTask(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            return new String[]{todoDescription};
        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split(" /by ");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);
            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }
            return new String[]{deadlineInputs[0].trim(), deadlineInputs[1].trim()};
        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split(" /from ");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);
            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }
            String[] dateRange = eventInputs[1].split(" /to ");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }
            return new String[]{eventInputs[0].trim(), dateRange[0].trim(), dateRange[1].trim()};
        default:
            throw new DescriptionNotFoundException();
        }
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
        if (description.equals("")) {
           throw new DescriptionNotFoundException();
        }
   }

}
