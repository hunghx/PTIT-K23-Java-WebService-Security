package ra.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ra.security.entity.Account;
import ra.security.entity.Role;
import ra.security.entity.RoleName;
import ra.security.repository.IAccountRepository;
import ra.security.repository.IRoleRepository;

import java.util.HashSet;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
    @Bean
    public CommandLineRunner runner(IAccountRepository accountRepository, IRoleRepository roleRepository, PasswordEncoder encoder) {
        return args -> {

        };

    }
}
