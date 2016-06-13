package ar.com.nolg.service.cdi;

import javax.transaction.TransactionalException;

import ar.com.nolg.exception.BaseException;
import ar.com.nolg.model.BaseModel;
import ar.com.nolg.service.BasePersistenceManager;
import ar.com.nolg.service.exception.ServiceException;
import ar.com.nolg.util.CompareUtil;

public abstract class BasePersistenceManagerCDI<T extends BaseModel> extends BaseModelManagerCDI<T>
		implements BasePersistenceManager<T> {
	/**
	 */
	@Override
	public T save(T model) throws ServiceException {
		try {
			if (model.isNew()) {
				doValid(model);
				doBeforeAdd(model);
				doBeforeAddUpdate(model);
				doAdd(model);
				doAfterAdd(model);
			} else {
				doValid(model);
				doBeforeUpdate(model);
				doBeforeAddUpdate(model);
				doUpdate(model);
				doAfterUpdate(model);
			}
			doAfterAddUpdate(model);
		} catch (BaseException b) {
			throw new ServiceException(b.getMessages());
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return model;
	}

	/**
	 */
	@Override
	public T delete(Long modelID) throws ServiceException {
		T model = null;
		try {
			if (CompareUtil.isEmpty(model = get(modelID))) {
				// throw new
				// ValidationException(PM.getMe().getMsg(DatabaseMsgEnum.RECORD_NOT_FOUND));
			}
			doBeforeDelete(model);
			doDelete(model);
			doAfterDelete(model);
		} catch (BaseException b) {
			throw new ServiceException(b.getMessages());
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return model;
	}

	/**
	 */
	private void doValid(T model) throws BaseException {
		// model.valid();
	}

	/**
	 */
	private void doAdd(T model) throws BaseException {
		getEntityManager().persist(model);
	}

	/**
	 */
	private T doUpdate(T model) throws BaseException {
		return getEntityManager().merge(model);
	}

	/**
	 */
	private void doDelete(T model) throws BaseException {
		getEntityManager().remove(model);
	}

	/**
	 */
	protected void doBeforeAdd(T model) throws BaseException {
	}

	/**
	 */
	protected void doBeforeUpdate(T model) throws BaseException {
	}

	/**
	 */
	protected void doBeforeAddUpdate(T model) throws BaseException {
	}

	/**
	 */
	protected void doBeforeDelete(T model) throws BaseException {
	}

	/**
	 */
	protected void doAfterAdd(T model) throws BaseException {
	}

	/**
	 */
	protected void doAfterUpdate(T model) throws BaseException {
	}

	/**
	 */
	protected void doAfterAddUpdate(T model) throws BaseException {
	}

	/**
	 */
	protected void doAfterDelete(T model) throws BaseException {
	}
}