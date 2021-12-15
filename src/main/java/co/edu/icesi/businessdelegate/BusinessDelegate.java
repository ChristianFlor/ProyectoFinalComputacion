package co.edu.icesi.businessdelegate;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;

public interface BusinessDelegate {

	RestTemplate getRestTemplate();

	// PRODUCT
	List<Product> showProductList();

	Product addProduct(Product p);

	Product getProduct(Integer id);

	public void editProduct(Integer id, Product p);

	public void deleteProduct(Product p);

	// PRODUCTSUBCATEGORY
	public List<Productsubcategory> showProductsubcategoryList();

	// PRODUCTCATEGORY
	public List<Productcategory> showProductcategoryList();

	// UNITMEASURE
	public List<Unitmeasure> showUnitmeasureList();

	// TRANSACITONHISTORY
	public List<Transactionhistory> showTransactionhistoryList();

	public Transactionhistory addTransactionhistory(Transactionhistory th);

	public Transactionhistory getTransactionhistory(Integer id);

	public void editTransactionhistory(Integer id, Transactionhistory th);

	public void deleteTransactionhistory(Transactionhistory th);
}
