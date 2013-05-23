package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.tagmodifier.bags.SizeBag;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

import javax.validation.constraints.Size;

import static codetroopers.wicket.web.parsley.validator.ParsleySizeValidationBehaviors.*;

/**
 * ITagModifier adding minimum and maximum size to fields
 *
 * @author cgatay
 */
public class ParsleySizeTagModifier extends AbstractTagModifier<Size> {
    /**
     * we're using the existing behaviors to keep a correct DRYness
     */
    @Override
    public void modify(final FormComponent<?> component, final ComponentTag tag, final Size annotation) {
        SizeBag bag = new SizeBag(getLabelString(component),annotation.min(), annotation.max());
        final ParsleyMinLengthValidationBehavior minValidator =
                new ParsleyMinLengthValidationBehavior(annotation.min(),
                                                       getLocalizedMessage(component, annotation.message(), bag));
        minValidator.onComponentTag(component, tag);
        final ParsleyMaxLengthValidationBehavior maxValidator
                = new ParsleyMaxLengthValidationBehavior(annotation.max(),
                                                         getLocalizedMessage(component, annotation.message(), bag));
        maxValidator.onComponentTag(component, tag);
    }
}
