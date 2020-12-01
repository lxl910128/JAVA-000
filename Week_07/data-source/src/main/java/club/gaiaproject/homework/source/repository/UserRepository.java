package club.gaiaproject.homework.source.repository;

import club.gaiaproject.homework.source.domain.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
