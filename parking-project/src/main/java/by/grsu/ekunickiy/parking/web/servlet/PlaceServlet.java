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
import by.grsu.ekunickiy.parking.db.dao.impl.PlaceDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Place;
import by.grsu.ekunickiy.parking.web.dto.PlaceDto;

public class PlaceServlet extends HttpServlet {
	private static final IDao<Integer, Place> placeDao = PlaceDaoImpl.INSTANCE;

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
		List<Place> places = placeDao.getAll(); // get data

		List<PlaceDto> dtos = places.stream().map((entity) -> {
			PlaceDto dto = new PlaceDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setBusy(entity.getBusy());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("place-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String placeIdStr = req.getParameter("id");
		PlaceDto dto = new PlaceDto();
		if (!Strings.isNullOrEmpty(placeIdStr)) {
			// object edit
			Integer placeId = Integer.parseInt(placeIdStr);
			Place entity = placeDao.getById(placeId);
			dto.setId(entity.getId());
			dto.setBusy(entity.getBusy());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("place-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Place place = new Place();
		String placeIdStr = req.getParameter("id");
        String busy = req.getParameter("busy");

		place.setBusy(busy == null ? null : Integer.parseInt(busy));
		
        if (Strings.isNullOrEmpty(placeIdStr)) {
			// new entity
			placeDao.insert(place);
		} else {
			// updated entity
			place.setId(Integer.parseInt(placeIdStr));
			placeDao.update(place);
		}
		res.sendRedirect("/place"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		placeDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
