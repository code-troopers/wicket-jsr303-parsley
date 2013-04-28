package codetroopers.wicket.web.parsley;

import codetroopers.wicket.web.parsley.javascript.ParsleyJsReference;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.bean.validation.*;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Behavior to add to a Form. This behavior will automatically add parsley validation rules to its children
 * along with their Bean validation validator.
 *
 * You must call Parsley.register(application) for the parsley validators to be properly created
 *
 * @author cgatay
 */
public class ParsleyFormBehavior extends Behavior {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParsleyFormBehavior.class);

    /**
     * Contributes parsley.js when this validator is attached to a form
     *
     * @param component The form component that will be validated
     * @param response  the response object where header contributions should be written
     */
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(JavaScriptHeaderItem.forReference(new ParsleyJsReference(component.getLocale())));
    }

    @Override
    public void bind(Component component) {
        if (!(component instanceof Form)) {
            throw new WicketRuntimeException("This behavior can only be bound to Form");
        }
        final Boolean registeredMetaData = component.getApplication().getMetaData(Parsley.PARSLEY_REGISTERED);
        if (registeredMetaData == null) {
            throw new WicketRuntimeException("Bean validation is not properly registered. " +
                    "Please call Parsley.register(Application.get())");
        }
        component.add(AttributeModifier.append("data-validate", "parsley"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onConfigure(Component component) {
        final BeanValidationContext beanValidationContext = BeanValidationConfiguration.get();
        ((Form) component).visitFormComponents(new IVisitor<FormComponent<?>, Void>() {
            @Override
            public void component(FormComponent<?> formComponent, IVisit<Void> visit) {
                final Property property = beanValidationContext.resolveProperty(formComponent);
                if (property != null) {
                    formComponent.add(new PropertyValidator());
                } else {
                    LOGGER.debug("Unable to attach parsley attributes to component {}, " +
                            "property is not accessible (consider using a model implementing {}",
                            formComponent, IPropertyResolver.class.getSimpleName());
                }
            }
        });
    }
}
