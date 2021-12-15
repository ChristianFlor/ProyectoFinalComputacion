package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Billofmaterial;
import co.edu.icesi.model.Productreview;

@Repository
@Scope("singleton")
public class BillofmaterialsDAO implements IBillofmaterialsDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public Billofmaterial save(Billofmaterial billofmaterial) {
		entityManager.persist(billofmaterial);
		return billofmaterial;
	}

	@Transactional
	@Override
	public Billofmaterial update(Billofmaterial billofmaterial) {
		entityManager.merge(billofmaterial);
		return billofmaterial;
	}

	@Transactional
	@Override
	public void delete(Billofmaterial billofmaterial) {
		entityManager.remove(billofmaterial);
	}

	@Override
	public Billofmaterial findById(Integer billofmaterialsid) {
		String jpql = "Select bom from Billofmaterial bom where bom.billofmaterialsid=: billofmaterialsid";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("billofmaterialsid", billofmaterialsid);
		Billofmaterial bom = null;
		try {
			bom = (Billofmaterial) query.getSingleResult();
		}catch(NoResultException e) {
		
		}
		return bom;
	}

	@Override
	public List<Billofmaterial> findAll() {
		String query = "Select bom from Billofmaterial bom";
		return entityManager.createQuery(query).getResultList();
	}

}
