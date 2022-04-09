package com.project.xanstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.project.xanstore.security.Permision.ADMIN_READ;
import static com.project.xanstore.security.Permision.ADMIN_WRITE;
import static com.project.xanstore.security.Role.ADMIN;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/js/**", "/css/**", "/img/**","/imgCategory/**","/imgProduct/**").permitAll()
                .antMatchers("/admin/pageToAddProduct").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/pageToAddProduct").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/addCategory").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/addCategory").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/addProduct").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/addProduct").hasAuthority(ADMIN_WRITE.getPermisionInfo())


                .antMatchers("/admin/editCategory/{id}").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/editCategory/{id}").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/open/editProduct/{id}").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/open/editProduct/{id}").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/open/editCategory/{id}").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/open/editCategory/{id}").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/pageToEditCategory").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/pageToEditCategory").hasAuthority(ADMIN_WRITE.getPermisionInfo())

                .antMatchers("/admin/pageToEditProduct").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/pageToEditProduct").hasAuthority(ADMIN_WRITE.getPermisionInfo())


                .antMatchers("/admin/addCategory").hasAuthority(ADMIN_READ.getPermisionInfo())
                .antMatchers("/admin/addCategory").hasAuthority(ADMIN_WRITE.getPermisionInfo())
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/home", true)
                .loginPage("/loginForAdmin")
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }


    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user_one = User.builder().
                username("admin1").password(passwordEncoder.encode("123"))
                .authorities(ADMIN.getGrantedAuthority()).build();
        return new InMemoryUserDetailsManager(user_one);
    }

}
