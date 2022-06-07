package eHealth.datagenerator;

import eHealth.config.DataGeneratorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class DataGeneratorManager implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ApplicationContext context;

    public DataGeneratorManager() {
        context = new AnnotationConfigApplicationContext(DataGeneratorConfig.class);
    }

    @PostConstruct
    private void generateData() {
        LOGGER.info("Generate Data");
        context.getBean(UserDataGenerator.class).generate();
        context.getBean(PractitionerDataGenerator.class).generate();
        context.getBean(AppointmentDataGenerator.class).generate();
        LOGGER.info("Finished generating Data");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
