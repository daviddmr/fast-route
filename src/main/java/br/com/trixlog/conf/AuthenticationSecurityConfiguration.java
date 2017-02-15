package br.com.trixlog.conf;

import br.com.trixlog.model.User;
import br.com.trixlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by kaynan on 19/01/17.
 */
@Configuration
public class AuthenticationSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    UserRepository userRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked()
                            , user.getRoles());
                } else {
                    throw new UsernameNotFoundException(
                            "O usuário " + username + "não existe.");
                }
            }
        });
    }
}
