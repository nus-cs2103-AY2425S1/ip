package commands;

public abstract class FindCommand extends Command {

    public static FindCommand createFindCommand(String description) {
        if (description.contains("tag") || description.contains("-t")) {
            String tags;
            if (description.contains("tag")) {
                tags = description.split("tag")[1];
            } else {
                tags = description.split("-t")[1];
            }

            tags = tags.trim();
            String[] tagArr = tags.split("\\s+");
            return new FilterByTagsCommand(tagArr);
        } else {
            return new FilterByDescriptionCommand(description);
        }
    }
}
