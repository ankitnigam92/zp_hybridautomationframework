package zp.bdd;

/**
 * Created by AnkitNigam.
 */

import zp.common.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = Application.class)
@ComponentScan(basePackages = {"zp.common"})
@ContextConfiguration
public class SpringCukesIntegration {
}
