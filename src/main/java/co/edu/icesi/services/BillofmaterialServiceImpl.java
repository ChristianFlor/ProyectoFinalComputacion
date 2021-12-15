package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.IBillofmaterialsDAO;
import co.edu.icesi.dao.IProductDAO;
import co.edu.icesi.model.Billofmaterial;
import co.edu.icesi.model.Product;

@Service
public class BillofmaterialServiceImpl implements BillofmaterialService {

	private IBillofmaterialsDAO bomDAO;
	private IProductDAO productDAO;

	@Autowired
	public BillofmaterialServiceImpl(IBillofmaterialsDAO bomDAO, IProductDAO productDAO) {
		this.bomDAO = bomDAO;
		this.productDAO = productDAO;
	}

	@Override
	public void saveBillofmaterial(Billofmaterial billofmaterial, Integer productId) {
		Product product = productDAO.findById(productId);
		if (product == null) {
			throw new RuntimeException();
		} else {
			billofmaterial.setProduct1(product);
			bomDAO.save(billofmaterial);
		}
	}

	@Override
	public void editBillofmaterial(Billofmaterial billofmaterial, Integer productId) {

		Product product = productDAO.findById(productId);
		Billofmaterial bom = bomDAO.findById(billofmaterial.getBillofmaterialsid());
		if (product == null) {
			throw new RuntimeException();
		} else if (bom == null) {
			throw new RuntimeException();
		} else {
			Billofmaterial bomEntity = bom;
			bomEntity.setProduct1(product);
			bomEntity.setBomlevel(billofmaterial.getBomlevel());
			bomDAO.update(bomEntity);
		}
	}

	@Override
	public Iterable<Billofmaterial> findAll() {
		return bomDAO.findAll();
	}

	@Override
	public Billofmaterial findById(Integer id) {
		return bomDAO.findById(id);
	}

	@Override
	public void delete(Billofmaterial billofmaterial) {
		bomDAO.delete(billofmaterial);
	}
}
