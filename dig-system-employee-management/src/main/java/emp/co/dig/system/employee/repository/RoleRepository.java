package emp.co.dig.system.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.co.dig.system.employee.entity.RoleInfo;

@Repository
public interface RoleRepository extends JpaRepository<RoleInfo, Integer> {

    Optional<RoleInfo> findByRoleName(String roleName);  

}
