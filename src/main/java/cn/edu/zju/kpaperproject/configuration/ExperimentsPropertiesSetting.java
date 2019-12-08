package cn.edu.zju.kpaperproject.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * experiments_number.
 *
 * @author RichardLee
 * @version v1.0
 */
@Configuration
@ConfigurationProperties(prefix = "experiments")
@PropertySource("classpath:/config/experiments.properties")
@Setter
@Getter
public class ExperimentsPropertiesSetting {

    private String number;
}
