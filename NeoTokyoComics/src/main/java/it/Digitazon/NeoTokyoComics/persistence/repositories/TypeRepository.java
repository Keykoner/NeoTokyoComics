package it.Digitazon.NeoTokyoComics.persistence.repositories;

import it.Digitazon.NeoTokyoComics.persistence.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
