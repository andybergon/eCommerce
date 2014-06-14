package com.ecommerce.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.ProductFacade;
import com.ecommerce.facade.ProviderFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.Provider;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class ProviderController {
	@EJB
	private ProviderFacade providerFacade;

	@EJB
	private ProductFacade productFacade;

	@ManagedProperty(value = "#{portal}")
	private ECommercePortal portal;

	private Provider currentProvider;

	private List<Provider> providers;

	private String newProductCode;

	public ProviderController() {
		this.currentProvider = new Provider();
	}

	public String findProvider(Long id) {
		this.currentProvider = this.providerFacade.find(id);
		return "provider" + Utils.REDIRECT;
	}

	public String listProviders() {
		this.providers = this.providerFacade.findAll();
		return "providers" + Utils.REDIRECT;
	}

	public String addProduct() {
		Product product = this.productFacade.find(this.newProductCode);
		if (product != null) {
			this.currentProvider.getProducts().add(product);
			this.providerFacade.update(currentProvider);
		} else {
			this.portal.setMessage("Code provided does not correspond to any product.");
		}
		return "provider";
	}

	public ECommercePortal getPortal() {
		return portal;
	}

	public void setPortal(ECommercePortal portal) {
		this.portal = portal;
	}

	public ProviderFacade getProviderFacade() {
		return providerFacade;
	}

	public void setProviderFacade(ProviderFacade providerFacade) {
		this.providerFacade = providerFacade;
	}

	public Provider getCurrentProvider() {
		return currentProvider;
	}

	public void setCurrentProvider(Provider currentProvider) {
		this.currentProvider = currentProvider;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public String getNewProductCode() {
		return newProductCode;
	}

	public void setNewProductCode(String newProductCode) {
		this.newProductCode = newProductCode;
	}
}