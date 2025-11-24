import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBcrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123456";
        String encoded = encoder.encode(password);
        System.out.println("Senha: " + password);
        System.out.println("BCrypt: " + encoded);
    }
}
