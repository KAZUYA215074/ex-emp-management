package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員テーブルを操作するテーブルです。
 * 
 * @author kazuya.makida
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployee() {
		UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
		return updateEmployeeForm;
	}

	/**
	 * 従業員リストを表示させる
	 * 
	 * @param model リクエストフォームを使用する
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(String sort, Model model) {
		if (sort == null) {

			List<Employee> employees = employeeService.showList();
			model.addAttribute("employeeList", employees);

		}else {
			List<Employee> employees = employeeService.sort(sort);
			model.addAttribute("employeeList", employees);
		}
		
		return "/employee/list";
	}

	@RequestMapping("/sort")
	public String sort(String sort, Model model) {

		List<Employee> employees = employeeService.sort(sort);
		model.addAttribute("employeeList", employees);

		return "/employee/list";
	}

	/**
	 * 選択された従業員の詳細を表示する。
	 * 
	 * @param form  選択された従業員idを格納ずる
	 * @param model リクエストスコープを使用する
	 * @return 従業員詳細画面を表示する
	 */
	@RequestMapping("/showDetail")
	public String showDetail(UpdateEmployeeForm form, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));

		model.addAttribute("employee", employee);
		return "/employee/detail";
	}

	/**
	 * 扶養人数の更新.
	 * 
	 * @param form  更新する扶養人数が入ったフォーム
	 * @param model モデル
	 * @return 従業員一覧画面へリダイレクト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));

		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));

		employeeService.upDate(employee);

		model.addAttribute("employee", employee);

		return "/employee/detail";
	}
}
