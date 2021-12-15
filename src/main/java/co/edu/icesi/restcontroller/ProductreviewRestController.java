package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Productreview;
import co.edu.icesi.services.ProductreviewService;

@RestController
@RequestMapping("/api")
public class ProductreviewRestController {

	@Autowired
	ProductreviewService productreviewService;

	@GetMapping("/productreviewRest/list")
	public Iterable<Productreview> showProductreviewList() {
		return productreviewService.findAll();
	}

	@GetMapping("/productreviewRest/view/{id}")
	public Productreview viewProductreview(@PathVariable("id") Integer id) {

		return productreviewService.findById(id);
	}

	@PostMapping("/productreviewRest/addproductreview/")
	public void addProductreview(@RequestBody Productreview productreview) {
		productreviewService.saveProductreview(productreview, productreview.getProduct().getProductid());

	}

	@PutMapping("/productreviewRest/edit/{id}")
	public void editProductreview(@RequestBody Productreview productreview) {

		productreviewService.editProductreview(productreview, productreview.getProduct().getProductid());

	}
	@DeleteMapping("/productreviewRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productreviewService.delete(productreviewService.findById(id));
//		productDAO.delete(productDAO.findById(id));
	}
	
}
