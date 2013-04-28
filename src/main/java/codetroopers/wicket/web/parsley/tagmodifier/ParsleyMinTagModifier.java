package codetroopers.wicket.web.parsley.tagmodifier;

import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Min;

import static codetroopers.wicket.web.parsley.validator.ParsleyNumberValueValidationBehaviors.ParsleyMinValueValidationBehavior;

/**
 * @author cgatay
 */
public class ParsleyMinTagModifier implements ITagModifier<Min>{
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Min annotation) {
        final ParsleyMinValueValidationBehavior minValidator = new ParsleyMinValueValidationBehavior(annotation.value());
        minValidator.onComponentTag(component, tag);
    }
}
