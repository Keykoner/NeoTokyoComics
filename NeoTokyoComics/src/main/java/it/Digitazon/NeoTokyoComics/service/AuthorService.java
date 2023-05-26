package it.Digitazon.NeoTokyoComics.service;

import it.Digitazon.NeoTokyoComics.persistence.entities.Author;
import it.Digitazon.NeoTokyoComics.persistence.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//questa classe fornisce un'interfaccia per eseguire operazioni
// CRUD (Create, Read, Update, Delete) sull'entità Author utilizzando
// il repository AuthorRepository.
@Service //Questa annotazione viene utilizzata per dichiarare che questa
// classe è un componente di servizio all'interno dell'applicazione.
public class AuthorService {
@Autowired //Viene utilizzata questa annotazione
// per inserire l'istanza di AuthorRepository all'interno del service.
    private AuthorRepository authorRepository;
public List<Author> getAll(){ //Restituisce una lista di tutti gli oggetti Author presenti
    // nel database utilizzando il metodo findAll() fornito da AuthorRepository.
    return authorRepository.findAll();
}

public Author getById(long id) { //Restituisce un singolo oggetto Author in base all'ID
    // specificato utilizzando il metodo findById() fornito da AuthorRepository.
    Optional<Author> optionalAuthor = authorRepository.findById(id);
    if (optionalAuthor.isEmpty()){
        throw new IllegalStateException("Entity not found");
    }
    return optionalAuthor.get();
}
public Author create (Author newAuthor){
    newAuthor = authorRepository.save(newAuthor);
    return newAuthor;
    //Crea un nuovo oggetto Author nel database utilizzando il metodo save()
    // fornito da AuthorRepository e restituisce il nuovo oggetto creato.
}

public Author update(long id, Author updateAuthor){
    Optional<Author> optionalAuthor = authorRepository.findById(id);
    if (optionalAuthor.isEmpty()){
        throw new IllegalStateException("Entity not found");
    }
    Author entityToUpdate = optionalAuthor.get();
    updateAuthor.setId(entityToUpdate.getId());
    updateAuthor = authorRepository.save(updateAuthor);
    return updateAuthor;
    //Aggiorna un oggetto Author esistente nel database in base all'ID
    // specificato utilizzando il metodo findById() di AuthorRepository per
    // trovare l'entità da aggiornare,
    // quindi imposta le nuove proprietà e salva l'entità aggiornata nel database.
}

public Author delete(long id){
    Optional<Author> optionalAuthor = authorRepository.findById(id);
    if (optionalAuthor.isEmpty()){
        throw new IllegalStateException("Entity not found");
    }
    Author entityToDelete = optionalAuthor.get();
    authorRepository.delete(entityToDelete);
    return entityToDelete;
    // Cancella un oggetto Author esistente nel database in base all'ID specificato
    // utilizzando il metodo findById() di AuthorRepository per trovare l'entità da
    // eliminare, quindi la elimina utilizzando il
    // metodo delete() fornito da AuthorRepository e restituisce l'oggetto eliminato.
}

}
