package tugaskelompokb8.apap.situ.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity htpp) throws Exception {
        htpp
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/changePassword").permitAll()
                //Akses Admin TU
                .antMatchers("/jenis-lowongan/**").hasAnyAuthority("Admin TU")
                .antMatchers("/jenisSurat/**").hasAnyAuthority("Admin TU")
                .antMatchers("/lowongan/**").hasAnyAuthority("Admin TU")
                .antMatchers("/pinjaman/**").hasAnyAuthority("Admin TU")
                .antMatchers("/user/addUser").hasAnyAuthority("Admin TU")
                //Akses Admin TU, Guru, Siswa
                .antMatchers("/pengajuanSurat/add").hasAnyAuthority("Admin TU", "Guru", "Siswa")
                .antMatchers("/pengajuanSurat/delete/**").hasAnyAuthority("Admin TU", "Guru", "Siswa")
                .antMatchers("/pengajuanSurat/statuses").hasAnyAuthority("Admin TU", "Guru", "Siswa")
                //Akses Admin TU, Kepala Sekolah
                .antMatchers("/pengajuanSurat/update/**").hasAnyAuthority("Admin TU","Kepala Sekolah")
                //Akses API
                .antMatchers("/api/v1/situ/**").permitAll()
                .anyRequest().authenticated()
                //.anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//   @Autowired
//   public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication()
//               .passwordEncoder(encoder())
//               .withUser("sandi").password(encoder().encode("bhirama"))
//               .roles("Kepala Sekolah");
//   }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

}
