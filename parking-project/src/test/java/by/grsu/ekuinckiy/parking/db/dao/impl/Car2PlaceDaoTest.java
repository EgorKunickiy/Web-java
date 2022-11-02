package by.grsu.ekuinckiy.parking.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.ekuinckiy.parking.db.dao.IDao;
import by.grsu.ekuinckiy.parking.db.model.Car;
import by.grsu.ekuinckiy.parking.db.model.Car2Place;
import by.grsu.ekuinckiy.parking.db.model.Place;
import by.grsu.ekuinckiy.parking.db.model.Model;
import by.grsu.ekuinckiy.parking.db.model.Brand;
import by.grsu.ekuinckiy.parking.db.model.User;

public class Car2PlaceDaoTest extends AbstractTest {
    private static final IDao<Integer, Car2Place> car_2_placeDao = Car2PlaceDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
    private static final IDao<Integer, Place> placeDao = PlaceDaoImpl.INSTANCE;
    private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;
    private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Car2Place entity = new Car2Place();
		entity.setCarId(saveCar("audi", "Q5").getId());
		entity.setPlaceId(savePlace(1).getId());
        entity.setContractStart(getCurrentTime());
		entity.setContractEnd(getCurrentTime());
		car_2_placeDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testInsertWithoutCar() {
		Car2Place entity = new Car2Place();
		entity.setPlaceId(savePlace(0).getId());
        entity.setContractStart(getCurrentTime());
		entity.setContractEnd(getCurrentTime());
		car_2_placeDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Car2Place entity = new Car2Place();
		entity.setCarId(saveCar("opel", "astra").getId());
        entity.setPlaceId(savePlace(1).getId());
        entity.setContractStart(getCurrentTime());
		entity.setContractEnd(getCurrentTime());
		car_2_placeDao.insert(entity);

		Car newCar = saveCar("audi", "Q5");
        entity.setCarId(newCar.getId());
        entity.setContractEnd(getCurrentTime());
		car_2_placeDao.update(entity);

		Car2Place updatedEntity = car_2_placeDao.getById(entity.getId());
		Assertions.assertEquals(newCar.getId(), entity.getCarId());
		Assertions.assertEquals(updatedEntity.getPlaceId(), entity.getPlaceId());
		
    }

	@Test
	public void testDelete() {
		Car2Place entity = new Car2Place();
		entity.setCarId(saveCar("audi", "Q5").getId());
		entity.setPlaceId(savePlace(1).getId());
        entity.setContractStart(getCurrentTime());
		entity.setContractEnd(getCurrentTime());
		car_2_placeDao.insert(entity);

		car_2_placeDao.delete(entity.getId());

		Assertions.assertNull(car_2_placeDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Car2Place entity = new Car2Place();
		entity.setCarId(saveCar("audi", "Q5").getId());
		entity.setPlaceId(savePlace(1).getId());
        entity.setContractStart(getCurrentTime());
		entity.setContractEnd(getCurrentTime());
		car_2_placeDao.insert(entity);

		Car2Place selectedEntity = car_2_placeDao.getById(entity.getId());
		Assertions.assertEquals(entity.getCarId(), selectedEntity.getCarId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Car2Place entity = new Car2Place();
			entity.setCarId(saveCar("audi"+i, "Q5"+i).getId());
			entity.setPlaceId(savePlace(0).getId());
            entity.setContractStart(getCurrentTime());
		    entity.setContractEnd(getCurrentTime());
			car_2_placeDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, car_2_placeDao.getAll().size());
	}

	private Car saveCar(String brand, String model) {
		User userEntity = new User();
		userEntity.setFirstName("Ivan");
		userEntity.setLastName("Ivanov");
		userDao.insert(userEntity);
        
        Brand brandEntity = new Brand();
		brandEntity.setName(brand);
		brandDao.insert(brandEntity);

		Model modelEntity = new Model();
		modelEntity.setName(model);
		modelEntity.setBrandId(brandEntity.getId());
		modelDao.insert(modelEntity);
        
        Car entity = new Car();
		entity.setOwnerId(userEntity.getId());
		entity.setModelId(modelEntity.getId());
        entity.setVin("qwertyuikjhgfds");
		carDao.insert(entity);
		return entity;
	}

	private Place savePlace(int busy) {
		Place entity = new Place();
		entity.setBusy(busy);
		placeDao.insert(entity);

		return entity;
	}
}