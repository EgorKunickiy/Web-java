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
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Brand;
import by.grsu.ekunickiy.parking.web.dto.BrandDto;;


public class BrandServlet extends HttpServlet {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;

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
		List<Brand> brand = brandDao.getAll(); // get data

		List<BrandDto> dtos = brand.stream().map((entity) -> {
			BrandDto dto = new BrandDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());

			// build data for complex fields
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("brand-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String brandIdStr = req.getParameter("id");
		BrandDto dto = new BrandDto();
		if (!Strings.isNullOrEmpty(brandIdStr)) {
			// object edit
			Integer brandId = Integer.parseInt(brandIdStr);
			Brand entity = brandDao.getById(brandId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("brand-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Brand brand = new Brand();
		String brandIdStr = req.getParameter("id");

		brand.setName(req.getParameter("name"));
		if (Strings.isNullOrEmpty(brandIdStr)) {
			// new entity
			brandDao.insert(brand);
		} else {
			// updated entity
			brand.setId(Integer.parseInt(brandIdStr));
			brandDao.update(brand);
		}
		res.sendRedirect("/brand"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		brandDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}