package by.grsu.ekuinckiy.parking.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.CarDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.ModelDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.UserDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Brand;
import by.grsu.ekunickiy.parking.db.model.Car;
import by.grsu.ekunickiy.parking.db.model.Model;
import by.grsu.ekunickiy.parking.db.model.User;

public class CarDaoTest extends AbstractTest {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userAccountDao = UserDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		entity.setOwnerId(saveUser().getId());
		carDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testInsertWithoutOwner() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		carDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		carDao.insert(entity);

		Model newModel = saveModel("opel", "corsa");
		entity.setVin("new_WAUZZZ8K0BA003806");
		entity.setModelId(newModel.getId());
		carDao.update(entity);

		Car updatedEntity = carDao.getById(entity.getId());
		Assertions.assertEquals(newModel.getId(), updatedEntity.getModelId());
		Assertions.assertEquals("WAUZZZ8K0BA003806", updatedEntity.getVin()); // VIN should stay unchanged as DAO doesn't update it
	}

	@Test
	public void testDelete() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		carDao.insert(entity);

		carDao.delete(entity.getId());

		Assertions.assertNull(carDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Car entity = new Car();
		entity.setModelId(saveModel("audi", "Q5").getId());
		entity.setVin("WAUZZZ8K0BA003806");
		carDao.insert(entity);

		Car selectedEntity = carDao.getById(entity.getId());

		Assertions.assertEquals(entity.getModelId(), selectedEntity.getModelId());
		Assertions.assertEquals(entity.getVin(), selectedEntity.getVin());
		Assertions.assertEquals(0, selectedEntity.getOwnerId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Car entity = new Car();
			entity.setModelId(saveModel("audi"+i, "Q5"+i).getId());
			entity.setVin("WAUZZZ8K0BA003806"+i);
			carDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, carDao.getAll().size());
	}

	private User saveUser() {
		User entity = new User();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		userAccountDao.insert(entity);
		return entity;
	}

	private Model saveModel(String brand, String model) {
		Brand brandEntity = new Brand();
		brandEntity.setName(brand);
		brandDao.insert(brandEntity);

		Model modelEntity = new Model();
		modelEntity.setName(model);
		modelEntity.setBrandId(brandEntity.getId());
		modelDao.insert(modelEntity);

		return modelEntity;
	}
}