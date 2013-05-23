package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for size validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author cgatay
 */
public class ParsleySizeValidationBehaviors {
    public static class ParsleyMinLengthValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMinLengthValidationBehavior(long minLength, final String errorMessage) {
            this(minLength, errorMessage, "change");
        }
        public ParsleyMinLengthValidationBehavior(long minLength, final String errorMessage, final String... trigger) {
            final long value = minLength;
            if (value > 0) {
                on(trigger);
                constraint("minlength", value);
                error("minlength",errorMessage);
            }
        }
    }

    public static class ParsleyMaxLengthValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMaxLengthValidationBehavior(long maxLength, final String errorMessage) {
            this(maxLength, errorMessage, "change");
        }
        public ParsleyMaxLengthValidationBehavior(long maxLength, final String errorMessage, final String... trigger) {
            on(trigger);
            constraint("maxlength", maxLength);
            error("maxlength",errorMessage);
        }
    }

}
