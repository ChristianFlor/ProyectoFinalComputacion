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

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.model.Add;
import co.edu.icesi.model.Billofmaterial;

@Controller
@RequestMapping("/billofmaterials")
public class BillofmaterialController {

	@Autowired
	BusinessDelegate delegate;
	
	@GetMapping("")
	public String billofmaterialIndex(Model model) {
		model.addAttribute("billofmaterials", delegate.showBilofmaterialList());
		return "/billofmaterials/index";
	}

	@GetMapping("/edit/{id}")
	public String editBillofmaterial(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("billofmaterial", delegate.getBillofmaterial(id));
		model.addAttribute("products", delegate.showProductList());
		return "/billofmaterials/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProductreview(
			@ModelAttribute("billofmaterial") @Validated(Add.class) Billofmaterial
			billofmaterial, BindingResult result,
			Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {

			if (result.hasErrors()) {
				model.addAttribute("products", delegate.showProductList());

				return "billofmaterials/edit";
			}
			billofmaterial.setProduct1(billofmaterial.getProduct1());
			delegate.editBillofmaterial(id, billofmaterial);
//			productreviewService.editProductreview(productreview, productreview.getProduct().getProductid());
		}
		return "redirect:/billofmaterials";
	}

	@GetMapping("/add")
	public String addbillofmaterial(Model model) {
		model.addAttribute("billofmaterial", new Billofmaterial());
		model.addAttribute("products", delegate.showProductList());

		return "/billofmaterials/add";
	}

	@PostMapping("/add")
	public String addProductreviewPost(
			@ModelAttribute("billofmaterial") @Validated(Add.class) Billofmaterial billofmaterial, BindingResult result,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("products", delegate.showProductList());
				return "/billofmaterials/add";
			}
			billofmaterial.setProduct1(billofmaterial.getProduct1());
			delegate.addBillofmaterial(billofmaterial);
//			productreviewService.saveProductreview(productreview, productreview.getProduct().getProductid());
		}
		return "redirect:/billofmaterials";
	}

	@GetMapping("/delete/{id}")
	public String deleteBillofmaterial(@PathVariable("id") Integer id, Model model) {
		Billofmaterial billofmaterial = delegate.getBillofmaterial(id);
		delegate.deleteBillofmaterial(billofmaterial);
//		productreviewService.delete(productreview);
		model.addAttribute("billofmaterials", delegate.showBilofmaterialList());
		return "redirect:/billofmaterials";
	}

	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Billofmaterial billofmaterial = delegate.getBillofmaterial(id);

		model.addAttribute("billofmaterial", billofmaterial);

		return "billofmaterials/information";
	}
}
