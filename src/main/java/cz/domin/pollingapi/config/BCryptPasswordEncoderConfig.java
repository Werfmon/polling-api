package cz.domin.pollingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoderConfig() {
        return new BCryptPasswordEncoder();
    }
}
