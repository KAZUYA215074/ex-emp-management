package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;



/** 従業員テーブルを操作するリポジトリです。
 * 
 * @author kazuya.makida
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	/** 従業員リストすべてを取得する。
	 * 
	 * @return 取得した従業員リスト
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date";		
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		return employeeList;
	}
	public List<Employee> sort(String sort) {
		System.out.println(sort);
		
		String orderbyString = null;
		if("hire_date".equals(sort)) {
			orderbyString = "hire_date";
		}
		
		else if("gender".equals(sort)) {
			orderbyString = "gender";
		}
		
		else if("name".equals(sort)) {
			orderbyString = "name";
		}
		
		else if("salary".equals(sort)) {
			orderbyString = "salary";
		}
		
		else if("dependents_count".equals(sort)) {
			orderbyString = "dependents_count";
		}
		
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY " + orderbyString;		
		SqlParameterSource param = new MapSqlParameterSource().addValue("sort", sort);
		List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);
		System.out.println("sort2");
		return employeeList;
	}

	/** 従業員テーブルからidで検索を行う。 
	 * 
	 * @param id 検査kするid
	 * @return 検索結果の従業員情報
	 */
	public Employee load(Integer id) {
	
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return employee;
	}

	/** 入力されたデータを従業員テーブルに登録する。
	 * 
	 * @param employee 入力されたデータ。
	 */
	public void upate(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		if (employee.getId() == null) {
			String sql = "INSERT INTO employees VALUES (:id,:name,:image,:gender,:hireDate,:mailAddress,:zipCode,:address,:telephone,:salary,:characteristics,:dependentsCount)";
			
			template.update(sql, param);
		} else {
			String sql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
			template.update(sql, param);
		}
	}
}
