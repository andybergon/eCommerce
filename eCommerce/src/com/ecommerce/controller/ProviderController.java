package com.ecommerce.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ecommerce.facade.ProductFacade;
import com.ecommerce.facade.ProductSupplyFacade;
import com.ecommerce.facade.ProviderFacade;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductSupply;
import com.ecommerce.model.Provider;
import com.ecommerce.utils.Utils;

@ManagedBean
@SessionScoped
public class ProviderController {
	@EJB
	private ProviderFacade providerFacade;

	@EJB
	private ProductFacade productFacade;

	@EJB
	private ProductSupplyFacade productSupplyFacade;

	@ManagedProperty(value = "#{portal}")
	private ECommercePortal portal;

	private Provider currentProvider;
	private List<Provider> providers;
	private String newProductCode;
	private Integer newProductQuantity;

	public ProviderController() {
	}

	public String findProvider(Long id) {
		this.currentProvider = this.providerFacade.find(id);
		return "provider" + Utils.REDIRECT;
	}

	public String findAllProviders() {
		this.providers = this.providerFacade.findAll();
		return "providers" + Utils.REDIRECT;
	}

	public String addProduct() {
		Product product = this.productFacade.find(this.newProductCode);

		if (product != null) {
			boolean supplyExists = false;
			for (ProductSupply ps : this.currentProvider.getInventories()) {
				if (ps.getProduct().getCode().equals(newProductCode)) {
					ps.addQuantity(newProductQuantity);
					this.productSupplyFacade.update(ps);
					supplyExists = true;
					break;
				}
			}

			if (!supplyExists) {
				ProductSupply productSupply = new ProductSupply(newProductQuantity);
				productSupply.setProduct(product);
				productSupply.setProvider(currentProvider);
				this.productSupplyFacade.create(productSupply);
				this.currentProvider.getInventories().add(productSupply);
			}

			this.providerFacade.update(currentProvider);
			this.newProductCode = null;
			this.newProductQuantity = 0;
		}
		return "provider" + Utils.REDIRECT;
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

	public ProductFacade getProductFacade() {
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public ProductSupplyFacade getProductSupplyFacade() {
		return productSupplyFacade;
	}

	public void setProductSupplyFacade(ProductSupplyFacade productSupplyFacade) {
		this.productSupplyFacade = productSupplyFacade;
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

	public Integer getNewProductQuantity() {
		return newProductQuantity;
	}

	public void setNewProductQuantity(Integer newProductQuantity) {
		this.newProductQuantity = newProductQuantity;
	}
}