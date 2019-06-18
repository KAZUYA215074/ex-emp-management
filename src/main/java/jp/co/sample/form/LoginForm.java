package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

/**
 * ログイン画面で入力された情操を扱うフォームです。
 * 
 * @author user
 *
 */
public class LoginForm {
	
	/* メールアドレス */
	@NotBlank
	private String mailAddress;
	/* パスワード */
	@NotBlank
	private String password;
	
	public String toString() {
		return "mailAddress="+mailAddress+" password="+password;
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
