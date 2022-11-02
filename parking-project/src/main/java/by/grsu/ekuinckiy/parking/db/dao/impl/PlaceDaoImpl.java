package by.grsu.ekuinckiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.grsu.ekuinckiy.parking.db.dao.AbstractDao;
import by.grsu.ekuinckiy.parking.db.dao.IDao;
import by.grsu.ekuinckiy.parking.db.model.Place;

public class PlaceDaoImpl extends AbstractDao implements IDao<Integer, Place> {
	public static final PlaceDaoImpl INSTANCE = new PlaceDaoImpl();

	private PlaceDaoImpl() {
		super();
	}

	@Override
	public void insert(Place entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into place(busy) values(?)");
			pstmt.setInt(1, entity.getBusy());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "place"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Place entity", e);
		}
	}

	@Override
	public void update(Place entity) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void delete(Integer id) {
		throw new RuntimeException("not implemented");

	}

	@Override
	public Place getById(Integer id) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<Place> getAll() {
		throw new RuntimeException("not implemented");
	}
}