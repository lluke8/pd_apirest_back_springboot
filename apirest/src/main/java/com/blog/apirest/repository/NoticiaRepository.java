package com.blog.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.apirest.models.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	
	Noticia findById(long id);
	Noticia findByTitulo(String titulo);
}
