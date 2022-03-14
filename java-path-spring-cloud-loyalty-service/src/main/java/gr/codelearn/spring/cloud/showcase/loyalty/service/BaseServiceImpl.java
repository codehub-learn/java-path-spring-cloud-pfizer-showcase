package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.loyalty.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseEntity> extends AbstractLogComponent
		implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public T save(T entity) {
		logger.trace("Creating {}.", entity);
		return getRepository().save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<T> saveAll(List<T> entities) {
		logger.trace("Creating {}.", entities);
		return getRepository().saveAll(entities);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(T entity) {
		logger.trace("Updating {}.", entity);
		if (exists(entity.getId())) {
			getRepository().save(entity);
		} else {
			throw new NoSuchElementException("Could not perform update operation to a non-existent object.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void delete(T entity) {
		logger.trace("Deleting {}.", entity);
		if (exists(entity.getId())) {
			getRepository().delete(entity);
		} else {
			throw new NoSuchElementException("Could not perform delete operation to a non-existent object.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void deleteById(Long id) {
		logger.trace("Deleting entity matching id {}.", id);
		if (exists(id)) {
			getRepository().deleteById(id);
		} else {
			throw new NoSuchElementException("Could not perform delete operation to a non-existent object.");
		}
	}

	public boolean exists(T entity) {
		logger.trace("Checking if {} exists.", entity);
		return exists(entity.getId());
	}

	public boolean exists(Long id) {
		return getRepository().existsById(id);
	}

	public T findById(Long id) {
		logger.trace("Retrieving entity with id {}.", id);
		/*
		 * T findOne(ID id) (name in the old API) / Optional<T> findById(ID id) (name in the new API) relies on
		 * EntityManager.find() that performs an entity eager loading.
		 *
		 * T getOne(ID id) relies on EntityManager.getReference() that performs an entity lazy loading. So to ensure
		 * the effective loading of the entity, invoking a method on it is required.
		 */
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
	}

	public List<T> findAll() {
		logger.trace("Retrieving all entities.");
		return getRepository().findAll();
	}
}
