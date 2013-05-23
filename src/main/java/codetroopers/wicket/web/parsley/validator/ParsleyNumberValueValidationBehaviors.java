package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for number values validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author cgatay
 */
public class ParsleyNumberValueValidationBehaviors {
    public static class ParsleyMinValueValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMinValueValidationBehavior(final long minValue, final String errorMessage) {
            this(minValue, errorMessage, "change");
        }

        public ParsleyMinValueValidationBehavior(final long minValue, final String errorMessage, final String... trigger) {
            final long value = minValue;
            if (value > 0) {
                on(trigger);
                constraint("min", value);
                error("min",errorMessage);
            }
        }
    }

    public static class ParsleyMaxValueValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMaxValueValidationBehavior(long maxValue, final String errorMessage) {
            this(maxValue, errorMessage, "change");
        }

        public ParsleyMaxValueValidationBehavior(long maxValue, final String errorMessage,  final String... trigger) {
            on(trigger);
            constraint("max", maxValue);
            error("max",errorMessage);
        }
    }

}
