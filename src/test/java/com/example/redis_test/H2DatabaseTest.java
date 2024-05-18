package com.example.redis_test;

import com.example.redis_test.entity.User;
import com.example.redis_test.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class H2DatabaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("H2 DB를 사용하여 생성 및 조회가 가능하다")
    public void testH2DatabaseConnection() {
        // 새로운 User 엔티티를 생성하고 저장합니다.
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        // 저장된 User 엔티티를 ID를 통해 조회합니다.
        User retrievedUser = userRepository.findById(user.getId()).orElse(null);

        // 조회된 User 엔티티가 null이 아니고, 이름이 "John Doe"인지 확인합니다.
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getName()).isEqualTo("John Doe");
    }
}