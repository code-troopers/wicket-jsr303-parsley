package codetroopers.wicket.web.parsley.tagmodifier;

import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Max;

import static codetroopers.wicket.web.parsley.validator.ParsleyNumberValueValidationBehaviors.ParsleyMaxValueValidationBehavior;

/**
 * @author cgatay
 */
public class ParsleyMaxTagModifier implements ITagModifier<Max>{
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Max annotation) {
        final ParsleyMaxValueValidationBehavior maxValidator = new ParsleyMaxValueValidationBehavior(annotation.value());
        maxValidator.onComponentTag(component, tag);
    }
}
