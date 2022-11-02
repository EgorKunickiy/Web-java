package by.grsu.ekuinckiy.parking.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.grsu.ekuinckiy.parking.db.dao.AbstractDao;
import by.grsu.ekuinckiy.parking.db.dao.IDao;
import by.grsu.ekuinckiy.parking.db.model.User;

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
		throw new RuntimeException("not implemented");
	}

	@Override
	public void delete(Integer id) {
		throw new RuntimeException("not implemented");

	}

	@Override
	public User getById(Integer id) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<User> getAll() {
		throw new RuntimeException("not implemented");
	}
}