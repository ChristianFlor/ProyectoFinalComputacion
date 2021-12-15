package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Vendor;
import co.edu.icesi.services.VendorService;

@RestController
@RequestMapping("/api")
public class VendorRestController {
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping("/vendorRest/list")
	public Iterable<Vendor> showVendorList(){
		return vendorService.findAll();
	}

}
