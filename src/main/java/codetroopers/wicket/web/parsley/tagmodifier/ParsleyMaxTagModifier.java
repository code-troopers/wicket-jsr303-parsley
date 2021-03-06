package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.tagmodifier.bags.ValueBag;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Max;

import static codetroopers.wicket.web.parsley.validator.ParsleyNumberValueValidationBehaviors.*;

/**
 * @author cgatay
 */
public class ParsleyMaxTagModifier extends AbstractTagModifier<Max> {
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Max annotation) {
        ValueBag bag = new ValueBag(getLabelString(component), annotation.value());
        final ParsleyMaxValueValidationBehavior maxValidator =
                new ParsleyMaxValueValidationBehavior(annotation.value(),
                                                      getLocalizedMessage(component, annotation.message(), bag));
        maxValidator.onComponentTag(component, tag);
    }
}
