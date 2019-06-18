package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

/**
 * 登録画面に入力された情報を扱うフォームです
 * 
 * @author user
 *
 */
public class InsertAdministratorForm {
	
	/* 名前 */
	@NotBlank
	private String name;
	/*メールアドレス */
	@NotBlank
	private String mailAddress;
	/*パスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
	

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
