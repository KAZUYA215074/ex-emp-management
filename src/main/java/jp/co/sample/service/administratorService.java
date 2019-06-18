package jp.co.sample.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Item;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者を扱うサービスです
 * 
 * @author user
 *
 */
@Service
@Transactional
public class administratorService {

	/**
	 * メールアドレス検索結果をコントローラーに返す
	 * 
	 */
	@Autowired
	private AdministratorRepository repository;

	public List<Item> mailAddressCheck(String mailAddress) {
		return repository.mailAddressCheck(mailAddress);
	}

	/**
	 * 管理者情報を登録する。
	 * 
	 * @param administrator
	 */
	public void inset(Item administrator) {
		repository.insert(administrator);

	}

	/**
	 * 管理者でログインする。
	 * 
	 * @param mailAddress
	 * @param password
	 * @return
	 */
	public Item login(String mailAddress, String password) {

		return repository.findByMailAddressAndPassword(mailAddress, password);
	}
}
