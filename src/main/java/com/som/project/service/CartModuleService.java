package com.som.project.service;

import com.som.project.entity.CartModule;

public interface CartModuleService {

	public CartModule addToCart(Long custemerId, Long bookId, int quantity);

	public void deleteToCart(Long id);

}
