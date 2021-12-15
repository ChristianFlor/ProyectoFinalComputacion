package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Billofmaterial;

public interface IBillofmaterialsDAO {
	Billofmaterial save(Billofmaterial Billofmaterial);

	Billofmaterial update(Billofmaterial Billofmaterial);

	void delete(Billofmaterial Billofmaterial);

	Billofmaterial findById(Integer Billofmaterialid);

	List<Billofmaterial> findAll();
}
