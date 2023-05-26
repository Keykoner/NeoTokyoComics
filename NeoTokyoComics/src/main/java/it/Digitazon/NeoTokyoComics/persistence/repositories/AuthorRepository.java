package it.Digitazon.NeoTokyoComics.persistence.repositories;

import it.Digitazon.NeoTokyoComics.persistence.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
 //   questa classe permette di eseguire operazioni di persistenza sull'entità Author,
 //       come ad esempio salvare un nuovo autore nel database, recuperare un autore esistente,
  //      eliminare un autore o eseguire altre operazioni di query specifiche.