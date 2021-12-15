package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Add;
import co.edu.icesi.model.Productreview;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductreviewService;

@Controller
@RequestMapping("/product-reviews")
public class ProductreviewController {

	private ProductService productService;
	private ProductreviewService productreviewService;
	
	@Autowired
	public ProductreviewController(ProductService productService, ProductreviewService productreviewService) {
		this.productService = productService;
		this.productreviewService = productreviewService;
	}
	
	@GetMapping("")
	public String productreviewIndex(Model model) {
		model.addAttribute("productreviews", productreviewService.findAll());
		return "/product-reviews/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editProductreview(Model model, @PathVariable("id")Integer id) {
		model.addAttribute("productreview", productreviewService.findById(id));
		model.addAttribute("products", productService.findAll());
		return "/product-reviews/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEditProductreview(@ModelAttribute("productreview") @Validated(Add.class) Productreview productreview,
			BindingResult result, Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			
			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());

				return "product-reviews/edit";
			}
			productreview.setProduct(productreview.getProduct());
//			transactionhistoryService.editCorrect(trh, trh.getProduct().getProductid());
			productreviewService.editProductreview(productreview, id);
		}
		return "redirect:/product-reviews";
	}
	
	@GetMapping("/add")
	public String addProductreview(Model model) {
		model.addAttribute("productreview", new Productreview());
		model.addAttribute("products", productService.findAll());

		return "/product-reviews/add";
	}
	
	@PostMapping("/add")
	public String addTransactionhistoryPost(@ModelAttribute("productreview") @Validated(Add.class) Productreview productreview,
			BindingResult result, @RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());
				return "/product-reviews/add";
			}
			productreview.setProduct(productreview.getProduct());
			productreviewService.saveProductreview(productreview, productreview.getProduct().getProductid());
		}
		return "redirect:/product-reviews";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductreview(@PathVariable("id") Integer id, Model model) {
		Productreview productreview =productreviewService.findById(id);
		productreviewService.delete(productreview);
		model.addAttribute("productreviews", productreviewService.findAll());
		return "redirect:/productreviews";
	}

	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Productreview productreview = productreviewService.findById(id);

		model.addAttribute("productreview", productreview);

		return "product-reviews/information";
	}

	
}
