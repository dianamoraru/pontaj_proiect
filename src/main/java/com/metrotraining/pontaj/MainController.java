package com.metrotraining.pontaj;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	PontajRepository pontaj_repo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {
		Calendar calStart = Calendar.getInstance();

		// pontaj_repo.save(new Pontaj(cal.getTimeInMillis(),cal.getTimeInMillis()));
		ArrayList<Pontaj> pnt = pontaj_repo.findAll();
		int totalH = 0;
		int totalM = 0;
		int totalElH = 0;
		int totalElM = 0;
		for (Pontaj p : pnt) {
			totalElH = 0;
			totalElM = 0;

			System.out.println(p);
			long diffinMillis = p.getEndDate() - p.getStartDate();

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(diffinMillis);
			calStart.setTimeInMillis(p.getStartDate());

			if (calStart.get(calStart.DAY_OF_WEEK) == 6) {
				totalElM = cal.get(cal.MINUTE) - 30;
				totalM += totalElM;
				totalElH = ((cal.get(cal.HOUR_OF_DAY) - 2) - 6);
				totalH += totalElH;

				p.setHourDiff(totalElH);
				p.setMinDiff(totalElM);

				System.out.println(totalM + "  " + totalH + " " + (cal.get(cal.HOUR_OF_DAY)) + " "
						+ (cal.get(cal.HOUR_OF_DAY) - 2) + " ore " + cal.get(cal.MINUTE) + " minute ");

			} else {
				totalElM = cal.get(cal.MINUTE) - 60;
				totalM += totalElM;
				totalElH = ((cal.get(cal.HOUR_OF_DAY) - 2) - 8);
				totalH += totalElH;

				p.setHourDiff(totalElH);
				p.setMinDiff(totalElM);

				System.out.println(totalM + "  " + totalH + " " + (cal.get(cal.HOUR_OF_DAY) - 2) + " ore "
						+ cal.get(cal.MINUTE) + " minute ");
			}

		}
		System.out.println(totalH + "   " + totalM + "  ");
		totalH += totalM / 60;
		totalM = totalM % 60;
		System.out.println(totalH + "   " + totalM + "  ");
		model.addAttribute("pontajList", pnt);
		model.addAttribute("total", totalH + " h " + totalM + " min ");
		return "main";
	}

	@RequestMapping(value = "/addPontaj", method = RequestMethod.GET)
	public String addPage(Model model) {
		Calendar cal = Calendar.getInstance();
		// pontaj_repo.save(new Pontaj(cal.getTimeInMillis(),cal.getTimeInMillis()));
		ArrayList<Pontaj> pnt = pontaj_repo.findAll();

		for (Pontaj p : pnt) {
			System.out.println(p);
		}

		model.addAttribute("pontajList", pnt);

		return "add_pontaj";
	}

	@RequestMapping(value = "/addPontajDate", method = RequestMethod.GET)
	public String addPontaj(@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "startDateTime", required = false) String startDateTime,
			@RequestParam(value = "endDateTime", required = false) String endDateTime, Model model) {

		Calendar cal = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateStart;
		Date dateEnd;
		try {
			dateStart = formatter.parse(startDate + " " + startDateTime);
			dateEnd = formatter.parse(startDate + " " + endDateTime);
			cal.setTime(dateStart);
			Long startDateinMillis = cal.getTimeInMillis();
			cal.setTime(dateEnd);
			Long endDateinMillis = cal.getTimeInMillis();
			pontaj_repo.save(new Pontaj(startDateinMillis, endDateinMillis));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

}
