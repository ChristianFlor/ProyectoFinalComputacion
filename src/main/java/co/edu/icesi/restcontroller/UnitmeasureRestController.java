package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.services.UnitMeasureService;

@RestController
@RequestMapping("/api")
public class UnitmeasureRestController {

	@Autowired
	UnitMeasureService unitmeasureService;
	
	@GetMapping("/unitmeasureRest/list")
	public Iterable<Unitmeasure> showUnitmeasureList(){
		return unitmeasureService.findAll();
	}
}
