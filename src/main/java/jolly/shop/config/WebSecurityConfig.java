package jolly.shop.config;

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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/css/**", "/account/signup").permitAll() //인증 없이 접근 가능
                .anyRequest().authenticated() //인증 해야 접근 가능
                .and()
            .formLogin()
                .loginPage("/account/login") // 로그인 페이지 지정
                .usernameParameter("email")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    // Spring Security의 AuthenticationManagerBuilder를 사용해 jdbc 인증처리
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
                auth.jdbcAuthentication()
                    .passwordEncoder(passwordEncoder()) // 스프링에서 인증처리 할 때 passwordEncoder를 통해 비밀번호 암호화를 알아서 진행
                    .dataSource(dataSource) // 현재 동작하는 데이터소스를 가져옴
                    .usersByUsernameQuery("select email, password, enabled " // 인증(로그인)처리를 위한 쿼리문
                        + "from user "
                        + "where email = ?")
                    .authoritiesByUsernameQuery("select u.email, r.authority " // 권한 처리를 위한 쿼리문
                        + "from user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where u.email = ?");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() { // BeanCurrentlyInCreationException 오류 해결을 위해 static
        return new BCryptPasswordEncoder();
    }

}
