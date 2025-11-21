package com.som.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.som.project.entity.Customer;

public interface CustmerService {

	public Customer insertCustmers(Customer custmer);

	public Customer UpdatessCustmers(Customer custmer);

	public Customer createdOrUpdatessCustmers(Customer custmer);

	public Customer getByCustmersId(Long id);

	public List<Customer> getByAllCustm();

	public Page<Customer> getByAllCustmersWithPaginations(int page, int size, String sortField, String pageDir);

}
