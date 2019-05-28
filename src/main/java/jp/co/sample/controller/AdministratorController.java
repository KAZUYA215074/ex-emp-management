package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.administratorService;


/**
 * 管理者を扱うコントローラーです.
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	/*  */
	@Autowired
	private administratorService service;
	
	/*  */
	@ModelAttribute
	public InsertAdministratorForm setUpAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	}
	
	/* administrator/insert.htmlを開きます */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "/administrator/insert";
	}
	
	/* 入力された値をドメインに渡し、
	 * リポジトリのinsert処理を呼び出し、
	 * データベースに記録する処理です */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		service.inset(administrator);
		
		return"redirect:/";
	}
	
	/*  */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}
	
	/* administrator/login.htmlを開きます */
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}
	
	
}
