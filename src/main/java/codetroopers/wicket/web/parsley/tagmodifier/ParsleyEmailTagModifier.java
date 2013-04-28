package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.validator.ParsleyEmailValidationBehavior;
import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import java.lang.annotation.Annotation;

/**
 * To allow the use of this library without hibernate validator,
 * there is no dependency against the original org.hibernate.validator.constraints.Email class.
 *
 * @author cgatay
 */
public class ParsleyEmailTagModifier implements ITagModifier<Annotation>{
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Annotation annotation) {
        final ParsleyEmailValidationBehavior emailValidator = new ParsleyEmailValidationBehavior();
        emailValidator.onComponentTag(component, tag);
    }
}
