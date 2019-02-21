package com.blog.apirest.resources;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apirest.repository.NoticiaRepository;
import com.blog.apirest.models.Noticia;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/api")
public class NoticiaResource {
	
	@Autowired
	NoticiaRepository noticiaRepository;
	
	@PostMapping(value="/noticias", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> store(@RequestParam Map<String, String> noticia) {
        Noticia n = noticiaRepository.findByTitulo(noticia.get("titulo"));
        
        if (n != null) {
        	return new ResponseEntity<>(n, HttpStatus.CONFLICT);
        }
        
        n = new Noticia();
        n.setAutor(noticia.get("autor"));
        n.setConteudo(noticia.get("conteudo"));
        n.setTitulo(noticia.get("titulo"));
       
        noticiaRepository.save(n);
        
        return new ResponseEntity<>(n, HttpStatus.CREATED);
    }
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value="/noticias", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Noticia> getAll(){        
        return noticiaRepository.findAll();
    }

	@GetMapping(value="/noticias", params={"id"}, produces=MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> getById(@RequestParam(value="id", required=true) Long id){
        Optional<Noticia> n = noticiaRepository.findById(id);
        
        if (!n.isPresent()) {
        	return new ResponseEntity<>("<p> Nao encontrado! </p>", HttpStatus.NOT_FOUND);  
        }
        
        return new ResponseEntity<>(getModal(n.get()), HttpStatus.OK);  
    }
	
	
	
	@DeleteMapping(value="/noticias/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deletaProduto(@PathVariable(value="id") long id) {
        Noticia n = noticiaRepository.findById(id);
        
        if (n == null) {
        	return new ResponseEntity<>(n, HttpStatus.NOT_FOUND);
        }
        
        noticiaRepository.delete(n);
        return new ResponseEntity<>(n, HttpStatus.OK);     
    }
	
	
	@PutMapping(value="/noticias/{id}/titulo/{titulo}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") long id, @PathVariable("titulo") String titulo) {
       
      
        Noticia n = noticiaRepository.findById(id);
        
        if (n == null) {
        	return new ResponseEntity<>(n, HttpStatus.NOT_FOUND);
        }
         
        n.setTitulo(titulo);
        noticiaRepository.save(n);      
        return new ResponseEntity<>(n, HttpStatus.OK);
        
    }
	
	public String getModal(Noticia n) {
	        return "<div class=\"modal-dialog\">" +
	"    			<div class=\"modal-content\">" +
	"    				<form>" +
	"    					<div class=\"modal-header\">" +
	"    						<h4 class=\"modal-title\">Editar notícia</h4>" +
	"    						<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>" +
	"    					</div>" +
	"    					<div class=\"modal-body\" id=\"modal-body-edit\">" +
	"                                           <div class=\"form-group\">" +
	"                                               <label>Título</label>" +
	"                                               <input type=\"text\" class=\"form-control\" name=\"nickname\" id=\"n_titulo_edit\" required value=\"" + n.getTitulo() + "\">" +
	"                                           </div>" +
	"                                           <div class=\"form-group\">" +
	"                                               <label>Autor</label>\n" +
	"                                               <input type=\"text\" class=\"form-control\" name=\"author\" id=\"n_autor_edit\" disabled value=\"" + n.getAutor()+ "\">" +
	"                                           </div>" +
	"                                           <div class=\"form-group\">" +
	"                                               <label>Conteúdo</label>" +
	"                                               <textarea class=\"form-control\" rows=\"5\" iname=\"content\" id=\"n_edit\" disabled style=\"resize: none;\">" + n.getConteudo()+ "</textarea>" +
	"                                           </div>" +
	"    					</div>\n" +
	"    					<div class=\"modal-footer\" id=\"modal-footer-edit\">\n" +
	"                                           <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Cancelar\">\n" +
	"                                           <input type=\"submit\" class=\"btn btn-success\"\n" +
	"                                           onclick=\"event.preventDefault(); editarNoticia(" + n.getId()+ ")\" value=\"Alterar\" id=\"noticia_edit\">" +
	"    					</div>\n" +
	"    				</form>\n" +
	"    			</div>\n" +
	"    		</div>";    
	}
}
