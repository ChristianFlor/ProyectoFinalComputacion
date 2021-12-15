package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.IProductDAO;
import co.edu.icesi.dao.IProductreviewDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productreview;

@Service
public class ProductreviewServiceImpl implements ProductreviewService {

	private IProductreviewDAO productreviewDAO;
	private IProductDAO productDAO;

	@Autowired
	public ProductreviewServiceImpl(IProductreviewDAO productreviewDAO, IProductDAO productDAO) {
		this.productreviewDAO = productreviewDAO;
		this.productDAO = productDAO;
	}

	@Override
	public void saveProductreview(Productreview productreview, Integer productId) {

		Product product = productDAO.findById(productId);
		if(product == null) {
			throw new RuntimeException();
		}else {
			productreview.setProduct(product);
			productreviewDAO.save(productreview);
		}
	}

	@Override
	public void editProductreview(Productreview productreview, Integer productId) {

		Product product = productDAO.findById(productId);
		Productreview pr = productreviewDAO.findById(productreview.getProductreviewid());
		if(product== null) {
			throw new RuntimeException();
		}else if( pr == null) {
			throw new RuntimeException();
		}else {
			Productreview prEntity = pr;
			prEntity.setProduct(product);
			productreviewDAO.update(prEntity);
		}
	}

	@Override
	public Iterable<Productreview> findAll() {
		return productreviewDAO.findAll();
	}

	@Override
	public Productreview findById(Integer id) {
		return productreviewDAO.findById(id);
	}

	@Override
	public void delete(Productreview productreview) {
		productreviewDAO.delete(productreview);
	}

}
