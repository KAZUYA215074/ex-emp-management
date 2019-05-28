package jp.co.sample.form;


/**
 * ログイン画面で入力された情操を扱うフォームです。
 * 
 * @author user
 *
 */
public class LoginForm {
	
	/* メールアドレス */
	private String mailAddress;
	/* パスワード */
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
