package llama.data;

import java.util.ArrayList;

import llama.ui.Ui;
/**
 * Represents the list of tags
 */
public class TagList {
    private ArrayList<Tag> tagList;

    /**
     * Create a TagList object
     */
    public TagList() {
        this.tagList = new ArrayList<>();
    }

    /**
     * Creates a new tag and stores it
     *
     * @param tagTitle Title of tag to be created
     * @param ui user interface to give user information
     */
    public String createTag(String tagTitle, Ui ui) {
        String response = "";
        if (exists(tagTitle)) {
            return "Tag already exists!";
        }
        Tag tag = new Tag(tagTitle);
        this.tagList.add(tag);
        response += ui.displayString("Tag created: " + tag);
        return response;
    }

    /**
     * Checks tagList to see if tagTitle already exists
     *
     * @param tagTitle tagTitle to check
     * @return true if tagTitle already exists, else return false
     */
    private boolean exists(String tagTitle) {
        int len = this.tagList.size();
        for (int i = 0; i < len; i++) {
            Tag tag = this.tagList.get(i);
            if (tag.getTitle().equals(tagTitle.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
