package codetroopers.wicket.web.parsley.page;

import codetroopers.wicket.web.parsley.ParsleyFormBehavior;
import codetroopers.wicket.web.parsley.TestBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * User: cgatay
 * Date: 28/04/13
 * Time: 11:26
 */
public class ResolvablePropertiesPage extends WebPage {
    public ResolvablePropertiesPage() {
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("form");
        final TestBean testBean = new TestBean();
        form.add(new TextField("tf1", new PropertyModel(testBean, "attr1")));
        form.add(new TextField("tf2", new PropertyModel(testBean, "attr2")));
        form.add(new TextField("tf3", newTF3Model(testBean)));

        form.add(new ParsleyFormBehavior());
        add(form);
    }

    protected IModel<Long> newTF3Model(TestBean testBean) {
        return Model.of(testBean.getAttr3());
    }
}
