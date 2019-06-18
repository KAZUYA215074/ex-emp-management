package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービスです。
 * 
 * @author kazuya.makida
 *
 */
@Service
@Transactional
public class EmployeeService {
	

	/**
	 * 従業員者リストをコントローラーに引き渡します。
	 * 
	 */
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	public List<Employee> sort(String sort){
		return employeeRepository.sort(sort);
	}
	
	/**
	 * 従業員の詳細をコントローラーに引き渡します。
	 * 
	 * @param id
	 * @return
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員の扶養人数をアップデートします。
	 * 
	 * @param employee
	 */
	public void upDate(Employee employee) {
		employeeRepository.upate(employee);
	}
}
