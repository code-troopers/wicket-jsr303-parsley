package codetroopers.wicket.web.parsley;

import codetroopers.wicket.web.parsley.tagmodifier.ParsleyEmailTagModifier;
import codetroopers.wicket.web.parsley.tagmodifier.ParsleyMaxTagModifier;
import codetroopers.wicket.web.parsley.tagmodifier.ParsleyMinTagModifier;
import codetroopers.wicket.web.parsley.tagmodifier.ParsleyNotNullTagModifier;
import codetroopers.wicket.web.parsley.tagmodifier.ParsleyPatternTagModifier;
import codetroopers.wicket.web.parsley.tagmodifier.ParsleySizeTagModifier;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;

/**
 * Base class handling ITagModifier registration
 *
 * If Hibernate Validator is in classpath, org.hibernate.validator.constraints.Email will be registered.
 *
 * @author cgatay
 */
public class Parsley {
    static final Logger LOGGER = LoggerFactory.getLogger(Parsley.class);
    static final MetaDataKey<Boolean> PARSLEY_REGISTERED = new MetaDataKey<Boolean>() {
    };

    private static Class<Annotation> HV_EMAIL_CONSTRAINT;

    static {
        try {
            HV_EMAIL_CONSTRAINT = (Class<Annotation>) Class.forName("org.hibernate.validator.constraints.Email");
        } catch (ClassNotFoundException e) {
            LOGGER.info("Hibernate Validator not found, not registering Email constraint annotation");
        }
    }

    public static void register(Application application) {
        try {
            final BeanValidationConfiguration configuration = (BeanValidationConfiguration) BeanValidationConfiguration.get();
            LOGGER.info("Bean validation is already registered for this application, adding ParsleyTagModifiers.");
            registerTagModifiers(configuration);
        } catch (Exception e) {
            final BeanValidationConfiguration configuration = new BeanValidationConfiguration();
            registerTagModifiers(configuration);
            configuration.configure(application);
        }
        application.setMetaData(PARSLEY_REGISTERED, true);
        LOGGER.info("Bean validation is now registered.");
    }

    private static void registerTagModifiers(BeanValidationConfiguration configuration) {
        configuration.register(Size.class, new ParsleySizeTagModifier());
        configuration.register(Min.class, new ParsleyMinTagModifier());
        configuration.register(Max.class, new ParsleyMaxTagModifier());
        configuration.register(NotNull.class, new ParsleyNotNullTagModifier());
        configuration.register(Pattern.class, new ParsleyPatternTagModifier());
        if (HV_EMAIL_CONSTRAINT != null) {
            //this is only added if hibernate validator is in the classpath
            configuration.register(HV_EMAIL_CONSTRAINT, new ParsleyEmailTagModifier());
        }
    }
}
