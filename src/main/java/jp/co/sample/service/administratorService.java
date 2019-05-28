package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class administratorService {

	@Autowired
	private AdministratorRepository repository;
	
	public void inset(Administrator administrator) {
		
		repository.insert(administrator);
	}
	
	public void findByMailAddressPassword(String mailAddress,String password) {
		repository.findByMailAddressAndPassword(mailAddress, password);
	}
}
