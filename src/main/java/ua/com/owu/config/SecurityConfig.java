package ua.com.owu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by okten22 on 23.06.17.
 */

@Configuration
@EnableWebSecurity
@ComponentScan("ua.com.owu.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    public InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemory(){
        return new InMemoryUserDetailsManagerConfigurer<>();
    }

    @Autowired
    public void configureInMemory(AuthenticationManagerBuilder auth, AuthenticationProvider provider) throws Exception{
        inMemory().withUser("adm")
                .password("adm")
                .authorities("ROLE_ADMIN")
                .and()
                .configure(auth);
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .passwordParameter("ssoId")
                .usernameParameter("password")
                .and()
                .csrf();
    }

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//                .and().formLogin().loginPage("/login")
//                .usernameParameter("ssoId").passwordParameter("password")
//                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
//    }
}
