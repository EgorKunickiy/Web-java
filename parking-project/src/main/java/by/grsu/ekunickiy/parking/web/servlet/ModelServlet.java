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
import by.grsu.ekunickiy.parking.db.dao.impl.ModelDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Model;
import by.grsu.ekunickiy.parking.db.model.Brand;
import by.grsu.ekunickiy.parking.web.dto.ModelDto;
import by.grsu.ekunickiy.parking.web.dto.BrandDto;

public class ModelServlet extends HttpServlet {
	private static final IDao<Integer, Model> modelDao = ModelDaoImpl.INSTANCE;
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
		List<Model> models = modelDao.getAll(); // get data

		List<ModelDto> dtos = models.stream().map((entity) -> {
			ModelDto dto = new ModelDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setName(entity.getName());

			// build data for complex fields
            Brand brand = brandDao.getById(entity.getBrandId());
            dto.setBrandName(brand.getName());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("model-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String modelIdStr = req.getParameter("id");
		ModelDto dto = new ModelDto();
		if (!Strings.isNullOrEmpty(modelIdStr)) {
			// object edit
			Integer modelId = Integer.parseInt(modelIdStr);
			Model entity = modelDao.getById(modelId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setBrandId(entity.getBrandId());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allBrands", getAllBrandsDtos());
		req.getRequestDispatcher("model-edit.jsp").forward(req, res);
	}

	private List<BrandDto> getAllBrandsDtos() {
		return brandDao.getAll().stream().map((entity) -> {
			BrandDto dto = new BrandDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName() );
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Model model = new Model();
		String modelIdStr = req.getParameter("id");
		String brandIdStr = req.getParameter("brandId");

		model.setName(req.getParameter("name"));
		model.setBrandId(brandIdStr == null ? null : Integer.parseInt(brandIdStr));
		if (Strings.isNullOrEmpty(modelIdStr)) {
			// new entity
			modelDao.insert(model);
		} else {
			// updated entity
			model.setId(Integer.parseInt(modelIdStr));
			modelDao.update(model);
		}
		res.sendRedirect("/model"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		modelDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}