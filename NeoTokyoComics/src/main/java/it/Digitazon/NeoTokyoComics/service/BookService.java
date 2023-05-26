package it.Digitazon.NeoTokyoComics.service;

import it.Digitazon.NeoTokyoComics.persistence.entities.Books;
import it.Digitazon.NeoTokyoComics.persistence.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAll() {
        return  bookRepository.findAll();
    }

    public Books getById(long id) {
        Optional<Books> optionalBooks = bookRepository.findById(id);

        if (optionalBooks.isEmpty()){
            throw new IllegalStateException("Entity not found");
        }
        return optionalBooks.get();
    }

    public Books create(Books newBooks) {
        if (newBooks.getAuthor()== null || newBooks.getType() == null){
            throw new IllegalStateException("Artist and Type must not be null");
        }
        newBooks = bookRepository.save(newBooks);
        return newBooks;
    }

    public Books update(long id, Books updateBooks){
        if(updateBooks.getAuthor() == null || updateBooks.getType() == null){
            throw new IllegalStateException("Artist and Type must not be null");
        }
        Optional<Books> optionalBooks = bookRepository.findById(id);
          if (optionalBooks.isEmpty()){
              throw new IllegalStateException("Entity not found");
          }

          Books entityToUpdate = optionalBooks.get();
          updateBooks.setId(entityToUpdate.getId());
          updateBooks=bookRepository.save(updateBooks);
          return updateBooks;
    }


    public Books delete(long id) {
        Optional<Books> optionalBooks = bookRepository.findById(id);
        if (optionalBooks.isEmpty()){
            throw new IllegalStateException("Entity not found");
        }
        Books entityToDelete = optionalBooks.get();
        bookRepository.delete(entityToDelete);
        return entityToDelete;
    }








}
