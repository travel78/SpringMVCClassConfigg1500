package ua.com.owu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by okten22 on 26.06.17.
 */
@Configuration
@EnableWebSecurity
public class SecurityInit extends AbstractSecurityWebApplicationInitializer{
}
