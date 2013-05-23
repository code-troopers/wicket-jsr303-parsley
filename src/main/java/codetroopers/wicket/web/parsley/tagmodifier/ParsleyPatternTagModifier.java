package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.tagmodifier.bags.PatternBag;
import codetroopers.wicket.web.parsley.validator.ParsleyPatternValidationBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Pattern;

/**
 * @author bcousin
 */
public class ParsleyPatternTagModifier extends AbstractTagModifier<Pattern> {

    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Pattern annotation) {
        PatternBag bag = new PatternBag(getLabelString(component), annotation.regexp());
        final ParsleyPatternValidationBehavior patternValidator =
                new ParsleyPatternValidationBehavior(annotation.regexp(),
                                                     getLocalizedMessage(component, annotation.message(), bag));
        patternValidator.onComponentTag(component, tag);
    }
}
