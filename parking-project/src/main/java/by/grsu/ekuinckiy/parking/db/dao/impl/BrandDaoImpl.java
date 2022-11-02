package by.grsu.ekuinckiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ekuinckiy.parking.db.dao.AbstractDao;
import by.grsu.ekuinckiy.parking.db.dao.IDao;
import by.grsu.ekuinckiy.parking.db.model.Brand;

public class BrandDaoImpl extends AbstractDao implements IDao<Integer, Brand> {

	// single instance of this class to be used by the all consumers
	public static final BrandDaoImpl INSTANCE = new BrandDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private BrandDaoImpl() {
		super();
	}

	@Override
	public void insert(Brand entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into brand(name) values(?)");
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "brand"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Brand entity", e);
		}
	}

	@Override
	public void update(Brand entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update brand set name=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Brand entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from brand where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Brand entity", e);
		}

	}

	@Override
	public Brand getById(Integer id) {
		Brand entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from brand where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Brand entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Brand> getAll() {
		List<Brand> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from brand");
			while (rs.next()) {
				Brand entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Brand entities", e);
		}

		return entitiesList;
	}

	private Brand rowToEntity(ResultSet rs) throws SQLException {
		Brand entity = new Brand();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		return entity;
	}

}