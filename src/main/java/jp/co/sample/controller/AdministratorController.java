package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Item;
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

	@Autowired
	private administratorService service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}

	/**
	 * administrator/insert.htmlを開きます。
	 * 
	 * @return
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "/administrator/insert";
	}

	/**
	 * 管理者データベースに記録する処理です。
	 * 
	 * @param form 入力された管理者情報
	 * @return 次に開くhtmlのルートディレクトリ
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return this.toInsert();
		}

		List<Item> administrators = service.mailAddressCheck(form.getMailAddress());

		if (administrators.size() != 0) {
			model.addAttribute("error", "既に同じメールアドレスが登録されています");
			return this.toInsert();
		}

		Item administrator = new Item();
		BeanUtils.copyProperties(form, administrator);
		service.inset(administrator);

		return "redirect:/";
	}

	/**
	 * administrator/login.htmlを開きます
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}

	/**
	 * ログインする。
	 * 
	 * @param form  入力された値
	 * @param model リクエストスコープを使用する
	 * @return リダイレクト処理
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result, Model model) {

		Item administrator = service.login(form.getMailAddress(), form.getPassword());

		if(result.hasErrors()) {
			return this.toLogin();
		}
		
		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return toLogin();
		}

		session.setAttribute("administrator", administrator);
		return "forward:/employee/showList";
	}

	/**
	 * ログアウトする。
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();

		return "redirect:/";
	}
}
