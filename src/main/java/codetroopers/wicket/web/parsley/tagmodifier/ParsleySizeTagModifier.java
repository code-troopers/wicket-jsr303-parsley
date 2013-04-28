package codetroopers.wicket.web.parsley.tagmodifier;

import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Size;

import static codetroopers.wicket.web.parsley.validator.ParsleySizeValidationBehaviors.ParsleyMaxLengthValidationBehavior;
import static codetroopers.wicket.web.parsley.validator.ParsleySizeValidationBehaviors.ParsleyMinLengthValidationBehavior;

/**
 * ITagModifier adding minimum and maximum size to fields
 *
 * @author cgatay
 */
public class ParsleySizeTagModifier implements ITagModifier<Size>{
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Size annotation) {
        final ParsleyMinLengthValidationBehavior minValidator = new ParsleyMinLengthValidationBehavior(annotation.min());
        minValidator.onComponentTag(component, tag);
        final ParsleyMaxLengthValidationBehavior maxValidator = new ParsleyMaxLengthValidationBehavior(annotation.max());
        maxValidator.onComponentTag(component, tag);
    }
}
