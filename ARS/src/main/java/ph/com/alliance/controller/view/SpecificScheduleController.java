package ph.com.alliance.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.com.alliance.entity.Aircon;
import ph.com.alliance.entity.SpecificSchedule;
import ph.com.alliance.service.ARSService;

@Controller
@RequestMapping("/ars")
public class SpecificScheduleController {

	@Autowired
	private ARSService aRSService;

	@RequestMapping(value = "/aircon_schedule", method = RequestMethod.GET)
	public String viewAirconSchedule(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		System.out.println("-- AIRCON SCHEDULE VIEW --");

		List<SpecificSchedule> specific_schedule_list = aRSService.getSpecificScheduleList();
		List<Aircon> aircon_list = aRSService.getAirconList();

		String aircon_name;
		int a, b, aircon_sched_id, aircon_id;
		for (a = 0; a < aircon_list.size(); a++) {
			aircon_sched_id = specific_schedule_list.get(a).getAirconId();
			aircon_id = aircon_list.get(a).getId();
			System.out.println(aircon_sched_id + " : " + aircon_id);

			if (aircon_sched_id == aircon_id) {

				aircon_name = aircon_list.get(a).getName();
				System.out.println(aircon_name);
				map.addAttribute("aircon_name", aircon_name);
			}
		}

		map.addAttribute("specific_schedule_list", specific_schedule_list);

		return "ars/aircon_schedule";

	}

}
