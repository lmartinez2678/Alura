package com.alura.chalenge.literalura.repo;
import com.alura.chalenge.literalura.modelo.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepo extends JpaRepository<Libros, Long> {
    static Libros findByTituloIgnoreCase(String titulo) {
        return null;
    }

    static List<Libros> findByIdiomasContaining(String idioma) {
        return null;
    }
}
