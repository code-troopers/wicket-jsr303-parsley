package codetroopers.wicket.web.parsley.javascript;

import de.agilecoders.wicket.webjars.util.WebjarsVersion;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.ArrayList;

/**
 * Base parsley javascript reference
 *
 * @author cgatay
 */
public class ParsleyExtendJsReference extends JQueryPluginResourceReference {
    public ParsleyExtendJsReference() {
        super(ParsleyExtendJsReference.class, WebjarsVersion.useRecent("parsleyjs/current/dist/parsley.extend.min.js"));
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final JavaScriptReferenceHeaderItem parsleyJsReference = JavaScriptHeaderItem.forReference(new ParsleyJsReference());
        final Iterable<? extends HeaderItem> parentDependencies = super.getDependencies();
        final ArrayList<HeaderItem> headerItems = new ArrayList<HeaderItem>();
        for (HeaderItem parentDependency : parentDependencies) {
            headerItems.add(parentDependency);
        }
        headerItems.add(parsleyJsReference);
        return headerItems;
    }
}
