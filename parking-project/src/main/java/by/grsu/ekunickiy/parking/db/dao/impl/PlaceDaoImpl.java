package by.grsu.ekunickiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ekunickiy.parking.db.dao.AbstractDao;
import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.model.Place;

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
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update place set busy=? where id=?");
			pstmt.setInt(1, entity.getBusy());
			pstmt.setInt(2, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Place entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from place where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Place entity", e);
		}
	}

	@Override
	public Place getById(Integer id) {
		Place entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from place where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Place entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Place> getAll() {
		List<Place> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from place");
			while (rs.next()) {
				Place entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Place entities", e);
		}

		return entitiesList;
	}
	private Place rowToEntity(ResultSet rs) throws SQLException {
		Place entity = new Place();
		entity.setId(rs.getInt("id"));
		entity.setBusy(rs.getInt("busy"));
		return entity;
	}
}