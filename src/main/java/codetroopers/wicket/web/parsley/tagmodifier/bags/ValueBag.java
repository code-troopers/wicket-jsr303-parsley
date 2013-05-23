package codetroopers.wicket.web.parsley.tagmodifier.bags;

/**
 * User: bcousin
 */
public class ValueBag extends ValidatorBag {
    public long value;

    public ValueBag(final String label, final long value) {
        super(label);
        this.value = value;
    }
}
