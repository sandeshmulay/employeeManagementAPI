package emp.co.dig.system.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.co.dig.system.employee.entity.EmployeeInfo;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Integer> {

    Optional<EmployeeInfo> findByEmail(String email);  
    
}