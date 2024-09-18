package commands;

/**
 * An abstract class representing a command that finds tasks based on a description or tags.
 * This class provides a factory method to create a specific `FindCommand` based on the input description.
 */
public abstract class FindCommand extends Command {

    /**
     * Creates and returns a specific `FindCommand` based on the provided description.
     * If the description contains "tag" or "-t", it creates a `FilterByTagsCommand`.
     * Otherwise, it creates a `FilterByDescriptionCommand`.
     *
     * @param description The description used to determine the type of `FindCommand` to create.
     * @return A `FindCommand` object, either a `FilterByTagsCommand` or `FilterByDescriptionCommand`.
     */
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
