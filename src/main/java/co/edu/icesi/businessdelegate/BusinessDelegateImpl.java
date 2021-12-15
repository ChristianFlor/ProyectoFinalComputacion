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

import co.edu.icesi.model.Billofmaterial;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productreview;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Vendor;
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

	@Override
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

	@Override
	public void editTransactionhistory(Integer id, Transactionhistory th) {
		HttpEntity<Transactionhistory> request = new HttpEntity<>(th);

		template.put(baseurl + "/transactionhistoryRest/edit/" + id, request, Transactionhistory.class);
	}

	@Override
	public void deleteTransactionhistory(Transactionhistory th) {
		template.delete(baseurl + "/transactionhistoryRest/delete/" + th.getTransactionid());
	}

	// DOCUMENT
	@Override
	public List<Document> showDocumentList() {
		Document[] documentarray = template.getForObject(baseurl + "/documentRest/list", Document[].class);
		return Arrays.asList(documentarray);
	}

	@Override
	public Document addDocument(Document doc) {
		HttpEntity<Document> request = new HttpEntity<>(doc);
		return template.postForObject(baseurl + "/documentRest/addDocument/", request, Document.class);
	}

	@Override
	public Document getDocument(long id) {

		return template.getForObject(baseurl + "/documentRest/view/" + id, Document.class);
	}

	@Override
	public void editDocument(long id, Document doc) {
		HttpEntity<Document> request = new HttpEntity<>(doc);

		template.put(baseurl + "/documentRest/edit/" + id, request, Document.class);
	}

	@Override
	public void deleteDocument(Document doc) {
		template.delete(baseurl + "/documentRest/delete/" + doc.getDocumentnode());
	}

	// PRODUCT VENDOR
	@Override
	public List<Productvendor> showProductvendorList() {
		Productvendor[] productvendorarray = template.getForObject(baseurl + "/productvendorRest/list",
				Productvendor[].class);
		return Arrays.asList(productvendorarray);
	}

	@Override
	public Productvendor addProductvendor(Productvendor pv) {
		HttpEntity<Productvendor> request = new HttpEntity<>(pv);
		return template.postForObject(baseurl + "/productvendorRest/addProductvendor/", request, Productvendor.class);
	}

	@Override
	public Productvendor getProductvendor(Integer id) {

		return template.getForObject(baseurl + "/productvendorRest/view/" + id, Productvendor.class);
	}

	@Override
	public void editProductvendor(Integer id, Productvendor productvendor) {
		HttpEntity<Productvendor> request = new HttpEntity<>(productvendor);

		template.put(baseurl + "/productvendorRest/edit/" + id, request, Productvendor.class);
	}

	@Override
	public void deleteProductvendor(Productvendor productvendor) {
		template.delete(baseurl + "/productvendorRest/delete/" + productvendor.getId());
	}

	// VENDOR
	@Override
	public List<Vendor> showVendorList() {
		Vendor[] vendorarray = template.getForObject(baseurl + "/vendorRest/list", Vendor[].class);
		return Arrays.asList(vendorarray);
	}

	// PRODUCT REVIEW
	@Override
	public List<Productreview> showProductreviewList() {
		Productreview[] productvendorarray = template.getForObject(baseurl + "/productreviewRest/list",
				Productreview[].class);
		return Arrays.asList(productvendorarray);
	}

	@Override
	public Productreview addProductreview(Productreview productreview) {
		HttpEntity<Productreview> request = new HttpEntity<>(productreview);
		return template.postForObject(baseurl + "/productreviewRest/addproductreview/", request, Productreview.class);
	}

	@Override
	public Productreview getProductreview(Integer id) {

		return template.getForObject(baseurl + "/productreviewRest/view/" + id, Productreview.class);
	}

	@Override
	public void editProductreview(Integer id, Productreview productreview) {
		HttpEntity<Productreview> request = new HttpEntity<>(productreview);

		template.put(baseurl + "/productreviewRest/edit/" + id, request, Productreview.class);
	}
	
	@Override
	public void deleteProductreview(Productreview productreview) {
		template.delete(baseurl + "/productreviewRest/delete/" + productreview.getProductreviewid());
	}

	//BILL OF MATERIAL
	@Override
	public List<Billofmaterial> showBilofmaterialList() {
		Billofmaterial[] billofmaterialarray = template.getForObject(baseurl + "/billofmaterialRest/list",
				Billofmaterial[].class);
		return Arrays.asList(billofmaterialarray);
	}
	
	@Override
	public Billofmaterial addBillofmaterial(Billofmaterial billofmaterial) {
		HttpEntity<Billofmaterial> request = new HttpEntity<>(billofmaterial);
		return template.postForObject(baseurl + "/billofmaterialRest/addbillomaterial/", request, Billofmaterial.class);
	}
	
	@Override
	public Billofmaterial getBillofmaterial(Integer id) {

		return template.getForObject(baseurl + "/billofmaterialRest/view/" + id, Billofmaterial.class);
	}
	
	@Override
	public void editBillofmaterial(Integer id, Billofmaterial billofmaterial) {
		HttpEntity<Billofmaterial> request = new HttpEntity<>(billofmaterial);

		template.put(baseurl + "/billofmaterialRest/edit/" + id, request, Billofmaterial.class);
	}
	
	@Override
	public void deleteBillofmaterial(Billofmaterial billofmaterial) {
		template.delete(baseurl + "/billofmaterialRest/delete/" + billofmaterial.getBillofmaterialsid());
	}
}

