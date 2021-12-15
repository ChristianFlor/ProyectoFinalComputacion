package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Productcategory;
import co.edu.icesi.services.ProductCategoryService;

@RestController
@RequestMapping("/api")
public class ProductcategoryRestController {

	@Autowired
	ProductCategoryService productcategoryService;
	
	@GetMapping("/productcategoryRest/list")
	public Iterable<Productcategory> showProductcategoryList(){
		return productcategoryService.findAll();
	}
}
