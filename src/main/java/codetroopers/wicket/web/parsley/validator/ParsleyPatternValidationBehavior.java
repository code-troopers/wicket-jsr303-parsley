package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for pattern validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author bcousin
 */
public class ParsleyPatternValidationBehavior extends ParsleyValidationBehavior {
    public ParsleyPatternValidationBehavior(final String pattern) {
        this(pattern, "change");

    }

    public ParsleyPatternValidationBehavior(final String pattern, final String... trigger) {
        on(trigger);
        constraint("regexp", pattern);
    }
}
