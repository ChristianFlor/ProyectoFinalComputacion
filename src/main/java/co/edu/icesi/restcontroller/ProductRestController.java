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
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	ProductDAO productDAO;
	
	
	@GetMapping("/productsRest/list")
	public List<Product> showProductList(){
		log.info("SHOW PRODUCT LIST");
		return productDAO.findAll();
	}
	
	@GetMapping("/productsRest/view/{id}")
	public Product viewProduct(@PathVariable("id") Integer id){
		
		return productDAO.findById(id);
	}
	
    @PostMapping("/productsRest/addproduct/")
    public Product addProduct(@RequestBody Product p) {
        log.info("entro!");
        return productDAO.save(p);
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
