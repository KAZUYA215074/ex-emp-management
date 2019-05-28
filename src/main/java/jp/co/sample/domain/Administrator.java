package jp.co.sample.domain;


/**
 * 管理者の情報を表すドメインです.
 * 
 * @author user
 *
 */
public class Administrator {

	/* id */
	private Integer id;
	/* 名前 */
	private String name;
	/* メールアドレス */
	private String mailAddress;
	/* パスワード */
	private String password;

	public String toString() {
		return "id=" + id + " name=" + name + " mailaddress=" + mailAddress + " password=" + password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
