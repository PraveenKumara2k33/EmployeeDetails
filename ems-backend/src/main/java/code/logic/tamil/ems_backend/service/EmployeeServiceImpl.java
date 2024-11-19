package code.logic.tamil.ems_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import code.logic.tamil.ems_backend.dto.EmployeeDto;
import code.logic.tamil.ems_backend.entity.Employee;
import code.logic.tamil.ems_backend.exception.ResourceNotFoundException;
import code.logic.tamil.ems_backend.mapper.EmployeeMapper;
import code.logic.tamil.ems_backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee createEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(createEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given Id: "+ employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
				-> new ResourceNotFoundException("Employee is not exists with given Id: " + employeeId));
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
				-> new ResourceNotFoundException("Employee is not exists with given Id: " + employeeId));
		employeeRepository.deleteById(employeeId);
	}
}
