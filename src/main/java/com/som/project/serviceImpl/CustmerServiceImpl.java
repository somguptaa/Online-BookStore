package com.som.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.som.project.entity.Customer;
import com.som.project.exceptions.CustmerIDNotFoundException;
import com.som.project.repository.CustmerRepoo;
import com.som.project.service.CustmerService;

@Service
public class CustmerServiceImpl implements CustmerService {

	@Autowired
	CustmerRepoo custmerRepoo;

	@Override
	public Customer insertCustmers(Customer custmer) {

		Customer cus = custmerRepoo.save(custmer);

		return cus;
	}

	@Override
	public Customer UpdatessCustmers(Customer custmer) {

		Customer cus = custmerRepoo.save(custmer);

		return cus;
	}

	@Override
	public Customer createdOrUpdatessCustmers(Customer custmer) {

		if (custmer.getId() == null) {

			custmerRepoo.save(custmer);

		} else {

			Optional<Customer> byId = custmerRepoo.findById(custmer.getId());
			if (byId.isPresent()) {
				Customer existData = byId.get();
				existData.setName(custmer.getName());
				existData.setEmail(custmer.getEmail());
				custmerRepoo.save(existData);

			} else {

				throw new RuntimeException("Custmer Not Found");
			}
		}
		return custmer;
	}

	@Override
	public Customer getByCustmersId(Long id) {
		Optional<Customer> byId = custmerRepoo.findById(id);
		if(!byId.isPresent()) {
			
			throw new CustmerIDNotFoundException("Custmer Id Not Found");
		}
		return byId.get();
	}

	@Override
	public List<Customer> getByAllCustm() {
		List<Customer> list = custmerRepoo.findAll();
		
		return list;
	}


	
	@Override
	public Page<Customer> getByAllCustmersWithPaginations(int page, int size, String sortField, String pageDir) {
		
	Sort sort = pageDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		PageRequest request = PageRequest.of(page, size, sort);
		
		return custmerRepoo.findAll(request);
	}

}
