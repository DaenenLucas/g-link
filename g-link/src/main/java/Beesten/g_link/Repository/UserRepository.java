package Beesten.g_link.Repository;

import Beesten.g_link.Domain.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);

}
