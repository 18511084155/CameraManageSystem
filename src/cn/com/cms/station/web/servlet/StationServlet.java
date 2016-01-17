package cn.com.cms.station.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cms.camera.domain.Camera;
import cn.com.cms.station.domain.Station;
import cn.com.cms.station.service.StationService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class StationServlet
 */
@WebServlet("/StationServlet")
public class StationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private StationService stationService = new StationService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String findAll500KVStations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp
		 */
		List<Station> stations = stationService.findAll500KVStations();
		request.setAttribute("stations", stations);
		for (Station station : stations) {
			System.out.println(station.getStation_name_videoplant());
			for (Camera camera : station.getCameraList()) {
				System.out.println("       " + camera.getName());
			}
		}
		return "f:/jsps/left.jsp";
	}

}
