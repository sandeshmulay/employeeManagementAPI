package emp.co.dig.system.employee.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.co.dig.infra.exception.AccessTokenException;
import emp.co.dig.infra.exception.DataNotFoundException;
import emp.co.dig.infra.exception.DataNotSavedException;
import emp.co.dig.system.employee.constant.EmployeeConstant;
import emp.co.dig.system.employee.dto.EmployeeInfoResponseDto;
import emp.co.dig.system.employee.dto.EmployeeRequestDto;
import emp.co.dig.system.employee.entity.DepartmentInfo;
import emp.co.dig.system.employee.entity.EmployeeInfo;
import emp.co.dig.system.employee.entity.RoleInfo;
import emp.co.dig.system.employee.repository.DepartmentRepository;
import emp.co.dig.system.employee.repository.EmployeeRepository;
import emp.co.dig.system.employee.repository.RoleRepository;
import emp.co.dig.system.employee.service.EmployeeService;
import emp.co.dig.system.employee.util.AccessTokenUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void addEmployee(String accessToken, EmployeeRequestDto employeeRequest) {
		logger.info("Attempting to add employee: {}", employeeRequest);

		if (!AccessTokenUtil.isValidToken(accessToken)) {
			logger.warn("Invalid or expired access token provided: {}", accessToken);
			throw new AccessTokenException(EmployeeConstant.INVALID_OR_EXPIRED_TOKEN);
		}

		try {
			Optional<DepartmentInfo> department = departmentRepository.findById(employeeRequest.getDepartmentId());
			Optional<RoleInfo> role = roleRepository.findById(employeeRequest.getRoleId());

			if (!department.isPresent() || !role.isPresent()) {
				logger.warn("Department or role not found for request: {}", employeeRequest);
				throw new DataNotFoundException(EmployeeConstant.DATA_NOT_FOUND);
			}

			EmployeeInfo employee = new EmployeeInfo();
			employee.setName(employeeRequest.getName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setDepartment(department.get());
			employee.setRole(role.get());

			employeeRepository.save(employee);
			logger.info("Employee successfully added: {}", employeeRequest);

		} catch (DataNotFoundException e) {
			logger.error("Data not found error while adding employee: {}", e.getMessage());
			throw e;

		} catch (Exception e) {
			logger.error("Unexpected error occurred while adding employee: {}", e.getMessage(), e);
			throw new DataNotSavedException(EmployeeConstant.DATA_NOT_SAVED);
		}
	}

	@Override
	public EmployeeInfoResponseDto getEmployeeInformation(String accessToken, Integer id) {
		logger.info("Fetching employee information for ID: {}", id);

		if (!AccessTokenUtil.isValidToken(accessToken)) {
			logger.warn("Invalid or expired access token provided: {}", accessToken);
			throw new AccessTokenException(EmployeeConstant.INVALID_OR_EXPIRED_TOKEN);
		}

		Optional<EmployeeInfo> employee = employeeRepository.findById(id);
		if (!employee.isPresent()) {
			logger.warn("Employee not found for ID: {}", id);
			throw new DataNotFoundException(EmployeeConstant.DATA_NOT_FOUND);
		}

		EmployeeInfo entity = employee.get();
		EmployeeInfoResponseDto response = new EmployeeInfoResponseDto();
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setDepartmentName(entity.getDepartment().getDepartmentName());
		response.setRoleName(entity.getRole().getRoleName());

		logger.info("Employee information retrieved successfully for ID: {}", id);
		return response;
	}
}
