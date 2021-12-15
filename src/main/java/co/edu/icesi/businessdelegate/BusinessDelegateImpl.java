package co.edu.icesi.businessdelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BusinessDelegateImpl implements BusinessDelegate {

	private RestTemplate template;

	private final String baseurl = "http://localhost:8080/api";

	public BusinessDelegateImpl() {
		this.template = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		this.template.setMessageConverters(messageConverters);
	}

	public void setRestTemplate(RestTemplate resttemplate) {
		this.template = resttemplate;
	}

	@Override
	public RestTemplate getRestTemplate() {
		return template;
	}

	// PRODUCTS
	@Override
	public List<Product> showProductList() {
		Product[] productarray = template.getForObject(baseurl + "/productsRest/list", Product[].class);
		return Arrays.asList(productarray);
	}

	@Override
	public Product addProduct(Product p) {
		HttpEntity<Product> request = new HttpEntity<>(p);
		log.info("aqui entro");
		return template.postForObject(baseurl + "/productsRest/addproduct/", request, Product.class);
	}

	@Override
	public Product getProduct(Integer id) {

		return template.getForObject(baseurl + "/productsRest/view/" + id, Product.class);
	}

	@Override
	public void editProduct(Integer id, Product p) {
		HttpEntity<Product> request = new HttpEntity<>(p);

		template.put(baseurl + "/productsRest/edit/" + id, request, Product.class);
	}

	public void deleteProduct(Product p) {
		template.delete(baseurl + "/productsRest/delete/" + p.getProductid());
	}
	// PRODUCTSUBCATEGORIES

	@Override
	public List<Productsubcategory> showProductsubcategoryList() {
		Productsubcategory[] productsubcategoryarray = template.getForObject(baseurl + "/productsubcategoryRest/list",
				Productsubcategory[].class);
		return Arrays.asList(productsubcategoryarray);
	}

	// PRODUCTCATEGORIES

	public List<Productcategory> showProductcategoryList() {
		Productcategory[] productcategoryarray = template.getForObject(baseurl + "/productcategoryRest/list",
				Productcategory[].class);
		return Arrays.asList(productcategoryarray);
	}

	// UNITMEASURE
	public List<Unitmeasure> showUnitmeasureList() {
		Unitmeasure[] unitmeasurearray = template.getForObject(baseurl + "/unitmeasureRest/list", Unitmeasure[].class);
		return Arrays.asList(unitmeasurearray);
	}

	// TRANSACTIONHISTORY
	@Override
	public List<Transactionhistory> showTransactionhistoryList() {
		Transactionhistory[] transactionhistoryarray = template.getForObject(baseurl + "/transactionhistoryRest/list",
				Transactionhistory[].class);
		return Arrays.asList(transactionhistoryarray);
	}

	@Override
	public Transactionhistory addTransactionhistory(Transactionhistory th) {
		HttpEntity<Transactionhistory> request = new HttpEntity<>(th);
		return template.postForObject(baseurl + "/transactionhistoryRest/addtransactionhistory/", request,
				Transactionhistory.class);
	}
	
	@Override
	public Transactionhistory getTransactionhistory(Integer id) {

		return template.getForObject(baseurl + "/transactionhistoryRest/view/" + id, Transactionhistory.class);
	}
}
