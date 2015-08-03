package com.ricston.encryptedpwd;

import java.util.List;

public interface ContactRepository {
	
	public List<Contact> find(Integer offset, Integer rowCount);

}
