package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Billofmaterial;
import co.edu.icesi.services.BillofmaterialService;

@RestController
@RequestMapping("/api")
public class BillofmaterialRestController {

	@Autowired
	BillofmaterialService billofmaterialService;

	@GetMapping("/billofmaterialRest/list")
	public Iterable<Billofmaterial> showProductreviewList() {
		return billofmaterialService.findAll();
	}

	@PostMapping("/billofmaterialRest/addbillomaterial/")
	public void addBillofmaterial(@RequestBody Billofmaterial billofmaterial) {
		billofmaterialService.saveBillofmaterial(billofmaterial, billofmaterial.getProduct1().getProductid());

	}

	@GetMapping("/billofmaterialRest/view/{id}")
	public Billofmaterial viewBillofmaterial(@PathVariable("id") Integer id) {

		return billofmaterialService.findById(id);
	}

	@PutMapping("/billofmaterialRest/edit/{id}")
	public void editProductreview(@RequestBody Billofmaterial billofmaterial) {

		billofmaterialService.editBillofmaterial(billofmaterial, billofmaterial.getProduct1().getProductid());

	}

	@DeleteMapping("/billofmaterialRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		billofmaterialService.delete(billofmaterialService.findById(id));
	}
}
