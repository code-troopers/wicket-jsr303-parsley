package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for size validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author cgatay
 */
public class ParsleySizeValidationBehaviors {
    public static class ParsleyMinLengthValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMinLengthValidationBehavior(long minLength) {
            this(minLength, "change");
        }
        public ParsleyMinLengthValidationBehavior(long minLength, final String... trigger) {
            final long value = minLength;
            if (value > 0) {
                on(trigger);
                constraint("minlength", value);
            }
        }
    }

    public static class ParsleyMaxLengthValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMaxLengthValidationBehavior(long maxLength) {
            this(maxLength, "change");
        }
        public ParsleyMaxLengthValidationBehavior(long maxLength, final String... trigger) {
            on(trigger);
            constraint("maxlength", maxLength);
        }
    }

}
