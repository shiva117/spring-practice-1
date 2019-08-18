package com.adhokshaja.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adhokshaja.web.domain.Product;
import com.adhokshaja.web.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/list", "/" })
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAll());
		return "product/list";
	}

	@RequestMapping("/show/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "product/show";
	}

	@RequestMapping("/edit/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "product/productform";
	}

	@RequestMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product/productform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveOrUpdate(Product product) {
		Product newProduct = productService.saveOrUpdate(product);
		return "redirect:/product/show/" + newProduct.getId();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.delete(id);
		return "redirect:/product/list";
	}
}
