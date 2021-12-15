package co.edu.icesi.services;

import co.edu.icesi.model.Productreview;

public interface ProductreviewService {

	public void saveProductreview(Productreview productreview, Integer productId);

	public void editProductreview(Productreview productreview, Integer productId);

	public Iterable<Productreview> findAll();

	public Productreview findById(Integer id);

	public void delete(Productreview productreview);
}
