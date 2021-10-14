package br.com.cadastrofilme.crudhibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.cadastrofilme.model.Filme;

public class FilmeJpaDAO {

	private static FilmeJpaDAO instance;
	protected EntityManager entityManager;

	public static FilmeJpaDAO getInstance() {
		if (instance == null) {
			instance = new FilmeJpaDAO();
		}

		return instance;
	}

	private FilmeJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Filme getById(final int id) {
		return entityManager.find(Filme.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Filme> findAll() {
		return entityManager.createQuery("FROM " + Filme.class.getName()).getResultList();
	}

	public void persist(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			filme = entityManager.find(Filme.class, filme.getId());
			entityManager.remove(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Filme produto = getById(id);
			remove(produto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public List<Filme> returnFilmeList(int pagina, int qtdFilmes) {

		Session session = entityManager.unwrap(Session.class);
		
		Criteria crit = session.createCriteria(Filme.class);
		
		System.out.println("Página: " + pagina);
		
		
		int primeiroElemento = (pagina -1) * 10;
		
		System.out.println("Primeiro Elemento: "+primeiroElemento + 
				", quantidade de filmes: : "+qtdFilmes);
		
		crit.setFirstResult(primeiroElemento);
		crit.setMaxResults(qtdFilmes);
		List<Filme> results = crit.list();		
		
		
		return results;	
		
	}
	

}
