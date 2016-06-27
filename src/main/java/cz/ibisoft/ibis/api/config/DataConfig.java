package cz.ibisoft.ibis.api.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Richard Stefanca
 */
@Configuration
@EnableJpaAuditing
public class DataConfig {


}
