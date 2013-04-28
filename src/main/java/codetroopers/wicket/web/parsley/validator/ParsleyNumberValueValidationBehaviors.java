package codetroopers.wicket.web.parsley.validator;

/**
 * ParsleyValidators for number values validation, don't forget
 * to add the data-validation='parsley" to the parent form if you want it to work.
 *
 * @author cgatay
 */
public class ParsleyNumberValueValidationBehaviors {
    public static class ParsleyMinValueValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMinValueValidationBehavior(final long minValue) {
            this(minValue, "change");
        }

        public ParsleyMinValueValidationBehavior(final long minValue, final String... trigger) {
            final long value = minValue;
            if (value > 0) {
                on(trigger);
                constraint("min", value);
            }
        }
    }

    public static class ParsleyMaxValueValidationBehavior extends ParsleyValidationBehavior {
        public ParsleyMaxValueValidationBehavior(long maxValue) {
            this(maxValue, "change");
        }

        public ParsleyMaxValueValidationBehavior(long maxValue, final String... trigger) {
            on(trigger);
            constraint("max", maxValue);
        }
    }

}
