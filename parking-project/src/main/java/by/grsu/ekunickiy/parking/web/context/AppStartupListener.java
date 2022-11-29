package by.grsu.ekunickiy.parking.web.context;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.ekunickiy.parking.db.dao.AbstractDao;
import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.Car2PlaceDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.CarDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.ModelDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.PlaceDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.UserDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Brand;
import by.grsu.ekunickiy.parking.db.model.Car;
import by.grsu.ekunickiy.parking.db.model.Car2Place;
import by.grsu.ekunickiy.parking.db.model.Model;
import by.grsu.ekunickiy.parking.db.model.Place;
import by.grsu.ekunickiy.parking.db.model.User;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static final IDao<Integer, Place> placeDao = PlaceDaoImpl.INSTANCE;
	private static final IDao<Integer, Car2Place> car2placeDao = Car2PlaceDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Brand brandEntity = new Brand();
		brandEntity.setName("audi");
		brandDao.insert(brandEntity);
		System.out.println("created: " + brandEntity);

		Model modelEntity = new Model();
		modelEntity.setName("Q5");
		modelEntity.setBrandId(brandEntity.getId());
		modelDao.insert(modelEntity);
		System.out.println("created: " + modelEntity);

		User userEntity = new User();
		userEntity.setFirstName("Ivan");
		userEntity.setLastName("Ivanov");
		userDao.insert(userEntity);
		System.out.println("created: " + userEntity);

		Car carEntity = new Car();
		carEntity.setModelId(modelEntity.getId());
		carEntity.setVin("WAUZZZ8K0BA003806");
		carEntity.setOwnerId(userEntity.getId());
		carDao.insert(carEntity);
		System.out.println("created: " + carEntity);

		Place placeEntity = new Place();
		placeEntity.setBusy(1);
		placeDao.insert(placeEntity);
		System.out.println("created: " + placeEntity);

		Car2Place car2Place = new Car2Place();
		car2Place.setCarId(carEntity.getId());
		car2Place.setPlaceId(placeEntity.getId());
		car2Place.setContractStart(getCurrentTime());
		car2Place.setContractEnd(getCurrentTime());
		car2placeDao.insert(car2Place);
		System.out.println("created: " + car2Place);
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} 
		catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}