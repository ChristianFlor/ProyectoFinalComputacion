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

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.services.ProductVendorService;

@RestController
@RequestMapping("/api")
public class ProductvendorRestController {

	@Autowired
	ProductVendorService productvendorService;

	@GetMapping("/productvendorRest/list")
	public Iterable<Productvendor> showProductvendorList() {

		return productvendorService.findAll();
	}

	@GetMapping("/productvendorRest/view/{id}")
	public Productvendor viewProductvendor(@PathVariable("id") Integer id) {
		return productvendorService.findById(id).get();
	}

	@PostMapping("/productvendorRest/addProductvendor/")
	public void addProductvendor(@RequestBody Productvendor productvendor) {
		productvendorService.save(productvendor, productvendor.getUnitmeasurecode(), productvendor.getProductid(),
				productvendor.getVendor().getBusinessentityid());
	}

	@PutMapping("/productvendorRest/edit/{id}")
	public void editProductvendor(@RequestBody Productvendor productvendor) {
		productvendorService.edit(productvendor, productvendor.getUnitmeasurecode(), productvendor.getProductid(),
				productvendor.getVendor().getBusinessentityid());
	}
	
	@DeleteMapping("/productvendorRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productvendorService.delete(productvendorService.findById(id).get());
	}
}
