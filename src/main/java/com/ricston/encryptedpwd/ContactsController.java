package com.ricston.encryptedpwd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contacts")
public class ContactsController {

	@Autowired
	ContactRepository customerRepo;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public List<Contact> contacts(@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "rowCount", required = false) Integer rowCount) {
		return customerRepo.find(offset, rowCount);
	}
}
