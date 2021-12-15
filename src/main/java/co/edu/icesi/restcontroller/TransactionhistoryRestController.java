package co.edu.icesi.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dao.TransactionhistoryDAO;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.TransactionHistoryService;

@RestController
@RequestMapping("/api")
public class TransactionhistoryRestController {

	@Autowired
	TransactionhistoryDAO transactionhistoryDAO;

	@Autowired
	TransactionHistoryService transactionhistoryService;

	@GetMapping("/transactionhistoryRest/list")
	public Iterable<Transactionhistory> showTransactionhistoryList() {
		return transactionhistoryService.findAll();
	}

	@GetMapping("/transactionhistoryRest/view/{id}")
	public Transactionhistory viewTransactionhistory(@PathVariable("id") Integer id) {

		return transactionhistoryService.findById(id);
	}

	@PostMapping("/transactionhistoryRest/addtransactionhistory/")
	public void addTransactionhistory(@RequestBody Transactionhistory trh) {
		transactionhistoryService.saveCorrect(trh, trh.getProduct().getProductid());

	}

	@PutMapping("/transactionhistoryRest/edit/{id}")
	public void editTransactionhistory(@RequestBody Transactionhistory th) {

		transactionhistoryService.editCorrect(th, th.getProduct().getProductid());
	}

	@DeleteMapping("/transactionhistoryRest/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		transactionhistoryService.delete(transactionhistoryService.findById(id));
	}
}
