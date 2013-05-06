package codetroopers.wicket.web.parsley.javascript;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import de.agilecoders.wicket.webjars.util.WebjarsVersion;
import de.agilecoders.wicket.webjars.util.WicketWebjars;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.ArrayList;
import java.util.Locale;

import static de.agilecoders.wicket.webjars.util.WebjarsVersion.*;
import static de.agilecoders.wicket.webjars.util.WicketWebjars.*;

/**
 * Base parsley javascript reference
 *
 * @author cgatay
 */
public class ParsleyJsReference extends JQueryPluginResourceReference {
    public ParsleyJsReference() {
        this(null);
    }
    public ParsleyJsReference(final Locale locale) {
        super(WebjarsJavaScriptResourceReference.class, 
                useRecent(prependWebjarsPathIfMissing("parsleyjs/current/dist/parsley.min.js")), 
                locale, null, null);
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final Iterable<? extends HeaderItem> parentHeaderItems = super.getDependencies();
        if (getLocale() != null) {
            final ArrayList<HeaderItem> headerItems = new ArrayList<HeaderItem>();
            for (HeaderItem parentHeaderItem : parentHeaderItems) {
                headerItems.add(parentHeaderItem);
            }
            headerItems.add(JavaScriptHeaderItem.forReference(getI18nResourceReference()));
            return headerItems;
        }
        return parentHeaderItems;
    }

    /**
     * @return the resourceReference to use for i18nization in Parsley
     */
    protected JavaScriptResourceReference getI18nResourceReference() {
        return new WebjarsJavaScriptResourceReference("parsleyjs/current/i18n/messages." 
                + getLocale().getLanguage() + ".js");
    }
}
