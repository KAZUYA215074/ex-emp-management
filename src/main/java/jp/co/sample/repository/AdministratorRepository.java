package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Item;

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

	private static final RowMapper<Item> ADMINISTRATOR_RO_MAPPER = (rs, i) -> {
		Item administrator = new Item();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * 同じメールアドレスがないか検索する
	 * 
	 * @param mailAddress 入力されたメールアドレス
	 * @return 検索結果のリスト
	 */
	public List<Item> mailAddressCheck(String mailAddress) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);

		List<Item> administrators = template.query(sql, param, ADMINISTRATOR_RO_MAPPER);

		return administrators;
	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Item administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		if (administrator.getId() == null) {
			String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password)";
			template.update(sql, param);
		} else {
			String sql = "UPDATE administrators SET name=:name,mail_address=:mailAddress,password=:password WHERE id=:id";
			template.update(sql, param);
		}
	}

	/**
	 * 管理者情報をメールとパスワードで検索する。
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 取得した管理者情報
	 */
	public Item findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
				.addValue("password", password);

		try {
			Item administrator = template.queryForObject(sql, param, ADMINISTRATOR_RO_MAPPER);

			return administrator;

		} catch (EmptyResultDataAccessException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
