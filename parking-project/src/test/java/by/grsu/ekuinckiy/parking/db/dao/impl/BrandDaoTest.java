package by.grsu.ekuinckiy.parking.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Brand;

public class BrandDaoTest extends AbstractTest {
	private static final IDao<Integer, Brand> dao = BrandDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Brand entity = new Brand();
		entity.setName("VW");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Brand entity = new Brand();
		entity.setName("VW");
		dao.insert(entity);

		String newName = "VW_NEW";
		entity.setName(newName);
		dao.update(entity);

		Brand updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Brand entity = new Brand();
		entity.setName("VW");
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Brand entity = new Brand();
		entity.setName("VW");
		dao.insert(entity);

		Brand selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Brand entity = new Brand();
			entity.setName("VW" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
