package codetroopers.wicket.web.parsley.tagmodifier.bags;

import java.io.Serializable;

/**
 * User: bcousin
 */
public class ValidatorBag implements Serializable {
    public String label;

    public ValidatorBag(final String label) {
        this.label = label;
    }
}
