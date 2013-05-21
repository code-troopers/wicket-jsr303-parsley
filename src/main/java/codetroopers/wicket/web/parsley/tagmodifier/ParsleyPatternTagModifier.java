package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.validator.ParsleyPatternValidationBehavior;
import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Pattern;

/**
 * @author bcousin
 */
public class ParsleyPatternTagModifier implements ITagModifier<Pattern>{
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Pattern annotation) {
        final ParsleyPatternValidationBehavior patternValidator = new ParsleyPatternValidationBehavior(annotation.regexp());
        patternValidator.onComponentTag(component, tag);
    }
}
