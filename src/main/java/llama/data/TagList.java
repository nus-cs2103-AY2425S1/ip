package llama.data;

import java.util.ArrayList;

import llama.exceptions.InvalidTagException;
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
    public String createTag(String tagTitle, Ui ui) throws InvalidTagException {
        String response = "";
        if (exists(tagTitle) == -1) {
            throw new InvalidTagException("Tag already exists!");
        }
        Tag tag = new Tag(tagTitle);
        this.tagList.add(tag);
        response += ui.displayString("Tag created: " + tag);
        return response;
    }

    /**
     * Loads tag using given tag title, does not show user information
     */
    public void loadTag(String tagTitle) {
        Tag tag = new Tag(tagTitle);
        this.tagList.add(tag);
    }

    /**
     * Checks tagList to see if tagTitle already exists
     *
     * @param tagTitle tagTitle to check
     * @return true if tagTitle already exists, return index, else returns -1
     */
    private int exists(String tagTitle) {
        int len = this.tagList.size();
        for (int i = 0; i < len; i++) {
            Tag tag = this.tagList.get(i);
            if (tag.getTitle().equals(tagTitle.toLowerCase())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Get the size of tag list
     *
     * @return size of tag list
     */
    public int getSize() {
        return this.tagList.size();
    }

    /**
     * Get the tag at index i
     *
     * @param i index of tag to get
     * @return tag at index i
     */
    public Tag getTag(int i) {
        return this.tagList.get(i);
    }

    /**
     * Get the tag using tag title
     *
     * @param tagTitle title of tag to get
     * @return tag if found, else throw error
     */
    public Tag getTagByTitle(String tagTitle) throws InvalidTagException {
        int tagIndex = exists(tagTitle);
        if (tagIndex == -1) {
            throw new InvalidTagException("Tag does not exist! Create it using"
                    + " 'create <tag title>'!");
        }
        return getTag(tagIndex);
    }
}
