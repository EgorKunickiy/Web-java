package by.grsu.ekunickiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ekunickiy.parking.db.dao.AbstractDao;
import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.model.Car2Place;

public class Car2PlaceDaoImpl extends AbstractDao implements IDao<Integer, Car2Place> {
	public static final Car2PlaceDaoImpl INSTANCE = new Car2PlaceDaoImpl();

	private Car2PlaceDaoImpl() {
		super();
	}

	@Override
	public void insert(Car2Place entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into car_2_place(place_id, car_id, contract_start, contract_end) values(?,?,?,?)");
            pstmt.setObject(1, entity.getPlaceId());
            pstmt.setObject(2, entity.getCarId());
            pstmt.setTimestamp(3, entity.getContractStart());
			pstmt.setTimestamp(4, entity.getContractEnd());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "car_2_place"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Car_2_Place entity", e);
		}

	}

	@Override
	public void update(Car2Place entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update car_2_place set place_id=?, car_id=?, contract_start=?, contract_end=? where id=?");
			pstmt.setObject(1, entity.getPlaceId());
			pstmt.setObject(2, entity.getCarId());
			pstmt.setTimestamp(3, entity.getContractStart());
            pstmt.setTimestamp(4, entity.getContractEnd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Car_2_Place entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from car_2_place where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Car_2_Place entity", e);
		}
	}

	@Override
	public Car2Place getById(Integer id) {
		Car2Place entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from car_2_place where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Car_2_Place entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Car2Place> getAll() {
		List<Car2Place> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from car_2_place");
			while (rs.next()) {
				Car2Place entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Car_2_Place entities", e);
		}

		return entitiesList;
	}

	private Car2Place rowToEntity(ResultSet rs) throws SQLException {
		Car2Place entity = new Car2Place();
		entity.setId(rs.getInt("id"));
		entity.setPlaceId(rs.getInt("place_id"));
		entity.setCarId(rs.getInt("car_id"));
        entity.setContractStart(rs.getTimestamp("contract_start"));
        entity.setContractEnd(rs.getTimestamp("contract_end"));
		return entity;
	}

}