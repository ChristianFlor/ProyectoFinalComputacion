package co.edu.icesi.services;

import co.edu.icesi.model.Billofmaterial;

public interface BillofmaterialService {

	public void saveBillofmaterial(Billofmaterial billofmaterial, Integer productId);

	public void editBillofmaterial(Billofmaterial billofmaterial, Integer productId);

	public Iterable<Billofmaterial> findAll();

	public Billofmaterial findById(Integer id);

	public void delete(Billofmaterial billofmaterial);
}
