package utility;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class exists as a wrapper class for tags which is represented by a string.
 */
public class Tag implements Serializable {
    public static final Pattern TAG_REGEX_PATTERN = Pattern.compile("#\\w+");
    private final String tagName;

    /**
     * @param tagName String representation of the tag.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {
        this.tagName = "";
    }

    /**
     * extracts any hashtag prefixed words
     * @param unparsedString
     * @return a {@link Tag} object with extracted tag string
     */
    public static Tag parseTagFromRawString(String unparsedString) {
        Matcher m = TAG_REGEX_PATTERN.matcher(unparsedString);
        if (!m.find()) {
            return new Tag("");
        }
        return new Tag(m.group());
    }

    /**
     * check if match with input string
     * @param matchString
     * @return if input string is a match with our {@link Tag}
     */
    public boolean isMatch(String matchString) {
        return tagName.equalsIgnoreCase(matchString);
    }

    @Override
    public String toString() {
        return tagName;
    }
}
