package codetroopers.wicket.web.parsley.javascript;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.ArrayList;
import java.util.Locale;

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
        super(ParsleyJsReference.class, "parsley.min.js", locale, null, null);
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
        return new JavaScriptResourceReference(ParsleyJsReference.class,
                "i18n/messages." + getLocale().getLanguage() + ".js");
    }
}
