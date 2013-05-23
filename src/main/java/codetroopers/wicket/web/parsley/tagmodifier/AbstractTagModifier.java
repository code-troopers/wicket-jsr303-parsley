package codetroopers.wicket.web.parsley.tagmodifier;

import codetroopers.wicket.web.parsley.tagmodifier.bags.ValidatorBag;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.bean.validation.ITagModifier;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;

/**
 * User: bcousin
 */
public abstract class AbstractTagModifier<T extends java.lang.annotation.Annotation> implements ITagModifier<T> {
    protected String getLocalizedMessage(final FormComponent<?> component, String message,
                                         final ValidatorBag bag) {
        if (StringUtils.isNotEmpty(message)) {
            if (message.startsWith("{") && message.endsWith("}")) {
                return component.getLocalizer().getString(message.substring(1, message.length() - 1),
                                                          component,
                                                          Model.of(bag));
            }
            return message;
        }
        return null;
    }

    protected String getLabelString(final FormComponent<?> component) {
        if (component.getLabel() != null) {
            return component.getLabel().getObject();
        }
        return component.getInputName();
    }
}
