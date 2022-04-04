package jolly.shop.service;

import jolly.shop.domain.Role;
import jolly.shop.domain.User;
import jolly.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedpassword = passwordEncoder.encode(user.getPassword()); // 비밀번호 암호화
        user.setPassword(encodedpassword);

        user.setEnabled(true);

        Role role = new Role(); // Role 도메인을 추가로 만들지 않고 간단하게 하드코딩 처리
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

}
