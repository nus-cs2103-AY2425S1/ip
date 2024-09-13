package task;

import exceptions.InvalidTagNameException;

import java.util.ArrayList;
import java.util.HashMap;

public class TagManager {

    private static final HashMap<String, Tag> TAG_HASH_MAP = new HashMap<>();

    public static Tag addTag(String tagName) {
        // Only want 1 instance of every tag object in memory
        // All tasks that have the same tag should reference the same tag object
        String strippedName = tagName.trim().toLowerCase();
        if (TAG_HASH_MAP.containsKey(strippedName)) {
            return TAG_HASH_MAP.get(strippedName);
        }

        Tag newTag = new Tag(strippedName);
        TAG_HASH_MAP.put(strippedName, newTag);
        return newTag;
    }

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
