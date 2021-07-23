import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class Tests {

    /**
     * bcrypt 加密处理
     */
    @Test
    public void passwordEncode() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String userPassword = "123456";
        String clientSecret = "world";
        log.info("用户密码: {}", passwordEncoder.encode(userPassword));
        log.info("应用密钥: {}", passwordEncoder.encode(clientSecret));
    }
}
