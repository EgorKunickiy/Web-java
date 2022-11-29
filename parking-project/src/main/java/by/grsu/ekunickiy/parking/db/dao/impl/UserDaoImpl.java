package by.grsu.ekunickiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ekunickiy.parking.db.dao.AbstractDao;
import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.model.User;

public class UserDaoImpl extends AbstractDao implements IDao<Integer, User> {

	public static final UserDaoImpl INSTANCE = new UserDaoImpl();

	private UserDaoImpl() {
		super();
	}

	@Override
	public void insert(User entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user(first_name, last_name) values(?,?)");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert User entity", e);
		}
	}

	@Override
	public void update(User entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update user set first_name=?, last_name=? where id=?");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update User entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete User entity", e);
		}
	}

	@Override
	public User getById(Integer id) {
		User entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from user where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get User entity by id", e);
		}

		return entity;
	}

	@Override
	public List<User> getAll() {
		List<User> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from user");
			while (rs.next()) {
				User entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select User entities", e);
		}

		return entitiesList;
	}
	private User rowToEntity(ResultSet rs) throws SQLException {
		User entity = new User();
		entity.setId(rs.getInt("id"));
		entity.setFirstName(rs.getString("first_name"));
		entity.setLastName(rs.getString("last_name"));
		return entity;
	}
}