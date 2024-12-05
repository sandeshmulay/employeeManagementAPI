package emp.co.dig.system.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emp.co.dig.system.employee.entity.DepartmentInfo;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentInfo, Integer> {

    Optional<DepartmentInfo> findByDepartmentName(String departmentName);  
}