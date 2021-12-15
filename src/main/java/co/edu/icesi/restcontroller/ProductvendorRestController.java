package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/productvendorRest/addProductvendor/")
	public void addProductvendor(@RequestBody Productvendor productvendor) {
		productvendorService.save(productvendor, productvendor.getUnitmeasurecode(), productvendor.getProductid(),
				productvendor.getVendor().getBusinessentityid());
	}
}
