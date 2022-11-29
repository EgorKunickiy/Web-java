package by.grsu.ekunickiy.parking.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.grsu.ekunickiy.parking.db.dao.IDao;
import by.grsu.ekunickiy.parking.db.dao.impl.BrandDaoImpl;
import by.grsu.ekunickiy.parking.db.model.Brand;
import by.grsu.ekunickiy.parking.web.ValidationUtils;

public class BrandServletPrim extends HttpServlet {
	private static final IDao<Integer, Brand> brandDao = BrandDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String paramId = req.getParameter("id");

		// validation
		if (!ValidationUtils.isInteger(paramId)) {
			res.sendError(400); // send HTTP status 400 and close response
			return;
		}

		Integer brandId = Integer.parseInt(paramId); // read request parameter
		Brand brandById = brandDao.getById(brandId); // from DB

		res.setContentType("text/html");// setting the content type

		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");

		if (brandById == null) {
			pw.println("no brand by id=" + brandId);
		} else {
			pw.println(brandById.toString());
		}

		pw.println("</body></html>");
		pw.close();// closing the stream
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// get the stream to write the data
		pw.println("<html><body>");
		try {
			String paramName = req.getParameter("name");
			Brand brandEntity = new Brand();
			brandEntity.setName(paramName);
			brandDao.insert(brandEntity);
			pw.println("Saved:" + brandEntity);

		} catch (Exception e) {
			pw.println("Error:" + e.toString());
		}

		pw.println("</body></html>");
		pw.close();
	}
}