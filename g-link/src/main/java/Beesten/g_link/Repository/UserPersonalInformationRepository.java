package Beesten.g_link.Repository;

import Beesten.g_link.Domain.UserPersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalInformationRepository extends JpaRepository<UserPersonalInformation, Long> {
}
