package by.grsu.ekunickiy.parking.web.servlet;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.UserDaoImpl;
import by.grsu.ekunickiy.parking.db.model.User;
import by.grsu.ekunickiy.parking.web.dto.UserDto;

public class UserServlet extends HttpServlet {
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<User> users = userDao.getAll(); // get data

		List<UserDto> dtos = users.stream().map((entity) -> {
			UserDto dto = new UserDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setFirst_name(entity.getFirstName());
            dto.setLast_name(entity.getLastName());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("user-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userIdStr = req.getParameter("id");
		UserDto dto = new UserDto();
		if (!Strings.isNullOrEmpty(userIdStr)) {
			// object edit
			Integer userId = Integer.parseInt(userIdStr);
			User entity = userDao.getById(userId);
			dto.setId(entity.getId());
			dto.setFirst_name(entity.getFirstName());
            dto.setLast_name(entity.getLastName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("user-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		User user = new User();
		String userIdStr = req.getParameter("id");

		user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
		if (Strings.isNullOrEmpty(userIdStr)) {
			// new entity
			userDao.insert(user);
		} else {
			// updated entity
			user.setId(Integer.parseInt(userIdStr));
			userDao.update(user);
		}
		res.sendRedirect("/user"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		userDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}