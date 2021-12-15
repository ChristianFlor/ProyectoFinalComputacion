package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Productreview;

@Repository
@Scope("singleton")
public class ProductreviewDAO implements IProductreviewDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Productreview save(Productreview productreview) {
		entityManager.persist(productreview);
		return productreview;
	}

	@Override
	public Productreview update(Productreview productreview) {
		entityManager.merge(productreview);
		return productreview;
	}

	@Transactional
	@Override
	public void delete(Productreview productreview) {
		entityManager.remove(productreview);
	}

	@Override
	public Productreview findById(Integer productreviewid) {
		String jpql = "Select pr from Productreview pr where pr.productreviewid=: productreviewid";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("productreviewid", productreviewid);
		Productreview pr = null;
		try {
			pr = (Productreview) query.getSingleResult();
		}catch(NoResultException e) {
		
		}
		return pr;
	}

	@Override
	public List<Productreview> findAll() {
		String query = "Select pr from Productreview pr";
		return entityManager.createQuery(query).getResultList();
	}

}
