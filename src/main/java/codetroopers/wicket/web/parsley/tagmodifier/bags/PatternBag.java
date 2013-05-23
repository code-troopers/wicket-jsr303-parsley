package codetroopers.wicket.web.parsley.tagmodifier.bags;

/**
 * User: bcousin
 */
public class PatternBag extends ValidatorBag {
    public String regexp;

    public PatternBag(final String label, final String regexp) {
        super(label);
        this.regexp = regexp;
    }
}
