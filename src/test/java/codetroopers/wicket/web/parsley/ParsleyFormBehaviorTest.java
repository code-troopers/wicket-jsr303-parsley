package codetroopers.wicket.web.parsley;

import codetroopers.wicket.web.parsley.page.ResolvablePropertiesPage;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author cgatay
 */
public class ParsleyFormBehaviorTest {
    private WicketTester wicketTester;

    @Before
    public void setUp() throws Exception {
        wicketTester = new WicketTester();
    }

    @Test
    public void testThrowExceptionWhenNotRegistered(){
        final Form form = new Form("dummy");
        try {
            form.add(new ParsleyFormBehavior());
            Assert.fail("Registration check should have failed");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof WicketRuntimeException);
            Assert.assertTrue(e.getMessage().contains("is not properly registered"));
        }
    }

    @Test
    public void testThrowExceptionIfNotAttachingOnAForm(){
        Parsley.register(wicketTester.getApplication());
        final WebMarkupContainer notForm = new WebMarkupContainer("dummy");
        try {
            notForm.add(new ParsleyFormBehavior());
            Assert.fail("We are not supposed to be able to bind this behavior to anything else than Form");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof WicketRuntimeException);
            Assert.assertTrue(e.getMessage().contains("can only be bound to Form"));
        }
    }

    @Test
    public void testVisitFormComponents_onlyPropertyResolvable_areHandledAutomagically(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(ResolvablePropertiesPage.class);
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF1 = wicketTester.getTagByWicketId("tf1");
        Assert.assertTrue(tagTF1.hasAttribute("data-required"));
        Assert.assertEquals("true", tagTF1.getAttribute("data-required"));
        final TagTester tagTF2 = wicketTester.getTagByWicketId("tf2");
        Assert.assertTrue(tagTF2.hasAttribute("data-min"));
        Assert.assertEquals("4", tagTF2.getAttribute("data-min"));
        //tf3 is not added via a PropertyModel but via a wrong Model.of()
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertFalse(tagTF3.hasAttribute("data-max"));
    }

    @Test
    public void testVisitFormComponents_constraintCombinationProperlyHandled(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "attr3");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-max"));
        Assert.assertEquals("5", tagTF3.getAttribute("data-max"));
        Assert.assertTrue(tagTF3.hasAttribute("data-required"));
        Assert.assertEquals("true", tagTF3.getAttribute("data-required"));
    }

    @Test
    public void testVisitEmailFormComponent(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "emailAddress");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-type"));
        Assert.assertEquals("email", tagTF3.getAttribute("data-type"));
    }

    @Test
    public void testVisitSizeFormComponent(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "sizeAttr");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-minlength"));
        Assert.assertEquals("3", tagTF3.getAttribute("data-minlength"));
        Assert.assertTrue(tagTF3.hasAttribute("data-maxlength"));
        Assert.assertEquals("54", tagTF3.getAttribute("data-maxlength"));
    }

    @Test
    public void testVisitPatternFormComponent(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "patternAttr");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-regexp"));
        Assert.assertEquals("[a-zA-Z]", tagTF3.getAttribute("data-regexp"));
    }

    @Test
    public void testNotLocalizedCustomErrorMessage(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "notLocalizedCustomMessageAttr");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-max-message"));
        Assert.assertEquals("customMessage", tagTF3.getAttribute("data-max-message"));
    }

    @Test
    public void testLocalizedCustomErrorMessage(){
        Parsley.register(wicketTester.getApplication());
        wicketTester.startPage(new ResolvablePropertiesPage(){
            @Override
            protected IModel<Long> newTF3Model(TestBean testBean) {
                return new PropertyModel(testBean, "customMessageAttr");
            }
        });
        wicketTester.assertRenderedPage(ResolvablePropertiesPage.class);
        final TagTester tagTF3 = wicketTester.getTagByWicketId("tf3");
        Assert.assertTrue(tagTF3.hasAttribute("data-max-message"));
        Assert.assertEquals("Localized custom message", tagTF3.getAttribute("data-max-message"));
    }


}
