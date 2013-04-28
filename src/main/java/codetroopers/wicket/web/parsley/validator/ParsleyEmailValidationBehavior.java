package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for email validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author cgatay
 */
public class ParsleyEmailValidationBehavior extends ParsleyValidationBehavior {
    public ParsleyEmailValidationBehavior() {
        this("change");

    }
    public ParsleyEmailValidationBehavior(final String... trigger) {
        on(trigger);
        type("email");
    }
}
