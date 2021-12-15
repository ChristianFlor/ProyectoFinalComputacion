package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Productreview;

public interface IProductreviewDAO {

	Productreview save(Productreview productreview);
	Productreview update(Productreview productreview);
	void delete(Productreview productreview);
	Productreview findById(Integer productreviewid);
	List<Productreview> findAll();

}
