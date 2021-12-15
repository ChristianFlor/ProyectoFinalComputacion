package co.edu.icesi.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.icesi.dao.ProductDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.services.ProductService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/productsRest/list")
	public Iterable<Product> showProductList(){
		log.info("SHOW PRODUCT LIST");
//		return productDAO.findAll();
		return productService.findAll();
	}
	
	@GetMapping("/productsRest/view/{id}")
	public Product viewProduct(@PathVariable("id") Integer id){
		
		return productService.findById(id);
//		return productDAO.findById(id);
	}
	
    @PostMapping("/productsRest/addproduct/")
    public void addProduct(@RequestBody Product product) {
        log.info("entro!");
        productService.saveCorrect(product,
				product.getProductsubcategory().getProductcategory().getProductcategoryid(),
				product.getProductsubcategory().getProductsubcategoryid(),
				product.getUnitmeasure1().getUnitmeasurecode());
//        return productDAO.save(p);
    }
	
	@PutMapping("/productsRest/edit/{id}")
	public Product editProduct(@RequestBody Product product){

		return productDAO.update(product);
	}
	
	@DeleteMapping("productsRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productDAO.delete(productDAO.findById(id));
	}
}
