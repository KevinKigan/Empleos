package com.example.empleos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSercurty extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from Users where username=?")
                .authoritiesByUsernameQuery("select u.username, p.profile from UserProfile up " +
                        "inner join Users u on u.id = up.idUser " +
                        "inner join Profiles p on p.id = up.idProfile " +
                        "where u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // Los recursos estáticos no requieren autenticación
            .antMatchers(
                    "/bootstrap/**",
                    "/images/**",
                    "/tinymce/**").permitAll()
            // Asignar permisos a URLs por ROLES
            .antMatchers("/vacants/**").hasAnyAuthority("SUPERVISOR","ADMIN")
            .antMatchers("/categories/**").hasAnyAuthority("SUPERVISOR","ADMIN")
            .antMatchers("/users/**").hasAnyAuthority("ADMIN")
                // Las vistas públicas no requieren autenticación
            .antMatchers("/home",
                    "/signup",
                    "/search",
                    "/vacants/view/**",
                    "/save",
                    "/bycript/**").permitAll()

            // Todas las demás URLs de la Aplicación requieren autenticación
            .anyRequest().authenticated()

            // El formulario de Login no requiere autenticacion
            .and().formLogin().loginPage("/login").permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
