package org.zahid.apps.om.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("classpath:configprops.properties")
@ConfigurationProperties(prefix = "om")
@Data
public class ConfigProperties {
    private Map<String, String> app;
    private Map<String, String> db;
}
