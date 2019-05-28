package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;



/**
 * 管理者テーブルを操作するリポジトリです。
 * 
 * @author user
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	
	private static final RowMapper<Administrator> ADMINISTRATOR_RO_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	

	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		if (administrator.getId() == null) {
			String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password)";
			template.update(sql, param);
		} else {
			String sql = "UPDATE administrators SET name=:name,mail_address=:mailAddress,password=:password WHERE id=:id";
			template.update(sql, param);
		}

		System.out.println("insert完了");
	}

		
	/**
	 * 管理者情報をメールとパスワードで検索する。
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return　取得した管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:pasword";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);

		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_RO_MAPPER);

		return administrator;
	}

}
