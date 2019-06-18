package jp.co.sample.form;

/** 
 * 従業員アップデート画面で入力された情操を扱うフォームです。
 * 
 * @author kazuya.makida
 *
 */
public class UpdateEmployeeForm {

	/* id */
	private String id;
	/* 扶養人数 */
	private String dependentsCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
}
