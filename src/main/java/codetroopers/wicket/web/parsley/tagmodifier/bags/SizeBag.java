package codetroopers.wicket.web.parsley.tagmodifier.bags;

/**
 * User: bcousin
 */
public class SizeBag extends ValidatorBag {
    public long min;
    public long max;

    public SizeBag(final String label, final long min, final long max) {
        super(label);
        this.min = min;
        this.max = max;
    }
}
