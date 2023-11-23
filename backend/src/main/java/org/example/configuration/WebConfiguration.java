
package org.example.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс для настроек веб-приложения
 */
@Configuration
@ConditionalOnProperty(value = "cors.allow", havingValue = "true", matchIfMissing = false)
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * Настройки CORS
     * @param registry CORS с заданными настройками
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowCredentials(true);
    }
}
