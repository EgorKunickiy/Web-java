package by.grsu.ekunickiy.parking.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.CarDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.PlaceDaoImpl;
import by.grsu.ekunickiy.parking.db.dao.impl.Car2PlaceDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Car;
import by.grsu.ekunickiy.parking.db.model.Place;
import by.grsu.ekunickiy.parking.db.model.Car2Place;
import by.grsu.ekunickiy.parking.web.dto.Car2PlaceDto;
import by.grsu.ekunickiy.parking.web.dto.PlaceDto;
import by.grsu.ekunickiy.parking.web.dto.CarDto;


public class Car2PlaceServlet extends HttpServlet {
	private static final IDao<Integer, Car> carDao = CarDaoImpl.INSTANCE;
	private static final IDao<Integer, Car2Place> car2placeDao = Car2PlaceDaoImpl.INSTANCE;
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
		List<Car2Place> car2place = car2placeDao.getAll(); // get data

		List<Car2PlaceDto> dtos = car2place.stream().map((entity) -> {
			Car2PlaceDto dto = new Car2PlaceDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setContractStart(entity.getContractStart());
			dto.setContractEnd(entity.getContractEnd());

			// build data for complex fields
            Car car = carDao.getById(entity.getCarId());
            dto.setCarId(car.getId());
            dto.setVin(car.getVin());

            Place place = placeDao.getById(entity.getPlaceId());
            dto.setPlaceId(place.getId());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("car2place-list.jsp").forward(req, res); // delegate request processing to JSP
	}


	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String car2placeIdStr = req.getParameter("id");
		Car2PlaceDto dto = new Car2PlaceDto();
		if (!Strings.isNullOrEmpty(car2placeIdStr)) {
			// object edit
			Integer car2placeId = Integer.parseInt(car2placeIdStr);
			Car2Place entity = car2placeDao.getById(car2placeId);
			dto.setId(entity.getId());
			dto.setPlaceId(entity.getPlaceId());
			dto.setCarId(entity.getCarId());
		}

		req.setAttribute("dto", dto);
		req.setAttribute("allPlaces", getAllPlacesDtos());
        req.setAttribute("allCars", getAllCarsDtos());
		req.getRequestDispatcher("car2place-edit.jsp").forward(req, res);
	}

	private List<PlaceDto> getAllPlacesDtos() {
		return placeDao.getAll().stream().map((entity) -> {
			PlaceDto dto = new PlaceDto();
			dto.setId(entity.getId());
			dto.setBusy(entity.getBusy());
			return dto;
		}).collect(Collectors.toList());
	}

    private List<CarDto> getAllCarsDtos() {
		return carDao.getAll().stream().map((entity) -> {
			CarDto dto = new CarDto();
			dto.setId(entity.getId());
			dto.setVin(entity.getVin());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Car2Place car2place = new Car2Place();
		String car2placeIdStr = req.getParameter("id");
		String carIdStr = req.getParameter("carId");
		String placeIdStr = req.getParameter("placeId");

		car2place.setCarId(carIdStr == null ? null : Integer.parseInt(carIdStr));
		car2place.setPlaceId(placeIdStr == null ? null : Integer.parseInt(placeIdStr));
		car2place.setContractEnd(new Timestamp(new Date().getTime()));
		if (Strings.isNullOrEmpty(car2placeIdStr)) {
			// new entity
			car2place.setContractStart(new Timestamp(new Date().getTime()));
			car2placeDao.insert(car2place);
            
		} else {
			// updated entity
			car2place.setId(Integer.parseInt(car2placeIdStr));
			car2placeDao.update(car2place);
		}
		res.sendRedirect("/car2place"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		car2placeDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
