package task;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.InvalidTagNameException;

/**
 * Manages tags for tasks, ensuring that each tag has only one instance in memory.
 * Provides methods to add tags and convert strings into tag objects.
 */
public class TagManager {

    private static final HashMap<String, Tag> TAG_HASH_MAP = new HashMap<>();

    /**
     * Adds a tag with the specified name. If the tag already exists, returns the existing tag.
     * This ensures that there is only one instance of each tag object in memory.
     * All tasks that have the same tag will reference the same tag object.
     *
     * @param tagName The name of the tag to add.
     * @return The Tag object corresponding to the specified name.
     */
    public static Tag addTag(String tagName) {
        String strippedName = tagName.trim().toLowerCase();
        if (TAG_HASH_MAP.containsKey(strippedName)) {
            return TAG_HASH_MAP.get(strippedName);
        }

        Tag newTag = new Tag(strippedName);
        TAG_HASH_MAP.put(strippedName, newTag);
        return newTag;
    }

    /**
     * Converts an array of String tag names to an ArrayList of Tag objects.
     * If a tag name does not exist in the tag manager, throws an InvalidTagNameException.
     *
     * @param arr An array of tag names to convert to Tag objects.
     * @return An ArrayList of Tag objects corresponding to the specified names.
     * @throws InvalidTagNameException If a tag name does not exist in the tag manager.
     */
    public static ArrayList<Tag> stringArrToTags(String[] arr) throws InvalidTagNameException {
        ArrayList<Tag> resultArr = new ArrayList<>();
        for (String tag: arr) {
            if (TAG_HASH_MAP.containsKey(tag.toLowerCase().trim())) {
                resultArr.add(TAG_HASH_MAP.get(tag));
            } else {
                throw new InvalidTagNameException();
            }
        }
        return resultArr;
    }
}
