package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.validator.ParsleyValidationBehavior;
import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.NotNull;

/**
 * @author cgatay
 */
public class ParsleyNotNullTagModifier implements ITagModifier<NotNull>{
    /**
     * required is put automatically if the ParsleyValidationBehavior is manually added to a component,
     * there is no need to extract a not null behavior.
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final NotNull annotation) {
        final ParsleyValidationBehavior validationBehavior = new ParsleyValidationBehavior();
        validationBehavior.require(true);
        validationBehavior.onComponentTag(component, tag);
    }
}
