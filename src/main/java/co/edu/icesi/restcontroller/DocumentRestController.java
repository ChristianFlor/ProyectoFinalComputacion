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

import co.edu.icesi.model.Document;
import co.edu.icesi.services.DocumentService;

@RestController
@RequestMapping("/api")
public class DocumentRestController {

	@Autowired
	DocumentService documentService;
	
	@GetMapping("/documentRest/list")
	public Iterable<Document> showDocumentList(){
		return documentService.findAll();
	}
	
	@GetMapping("/documentRest/view/{id}")
	public Document viewDocument(@PathVariable("id") long id) {
		return documentService.findById(id);
	}
	
	@PostMapping("/documentRest/addDocument/")
	public void addDocument(@RequestBody Document document) {
		documentService.saveCorrect(document, document.getProduct().getProductid());
	}
	
	@PutMapping("/documentRest/edit/{id}")
	public void editDocument(@RequestBody Document document) {
		documentService.editCorrect(document, document.getProduct().getProductid());
	}
	
	@DeleteMapping("/documentRest/delete/{id}")
	public void deleteDocument(@PathVariable("id") long id) {
		documentService.delete(documentService.findById(id));
	}
}
