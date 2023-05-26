package it.Digitazon.NeoTokyoComics.persistence.repositories;

import it.Digitazon.NeoTokyoComics.persistence.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}
