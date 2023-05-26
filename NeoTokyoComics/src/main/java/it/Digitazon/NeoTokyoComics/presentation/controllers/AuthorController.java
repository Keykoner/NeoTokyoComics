package it.Digitazon.NeoTokyoComics.presentation.controllers;

import it.Digitazon.NeoTokyoComics.persistence.entities.Author;
import it.Digitazon.NeoTokyoComics.persistence.entities.Books;
import it.Digitazon.NeoTokyoComics.presentation.dto.AuthorDTO;
import it.Digitazon.NeoTokyoComics.presentation.dto.BookDTO;
import it.Digitazon.NeoTokyoComics.service.AuthorService;
import it.Digitazon.NeoTokyoComics.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//questa classe gestisce le operazioni CRUD (Create, Read, Update, Delete)
// sugli autori e fornisce funzionalità per ottenere e gestire i libri associati a un autore specifico.

@RestController //Questa annotazione indica che la classe è un controller REST.
@RequestMapping("/author") //Specifica il percorso di base per tutte le richieste gestite da questa classe.
@CrossOrigin(origins = "http://localhost:3000") //Consente le richieste cross-origin da "http://localhost:3000",
// consentendo di accedere a questa API da un'applicazione front-end in esecuzione su quel dominio.
public class AuthorController {

    @Autowired //semplifica lo sviluppo
    private AuthorService authorService; // Sono servizi che forniscono funzionalità per gestire gli autori e i libri.

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper; //È una libreria per il mapping degli oggetti che semplifica la conversione tra oggetti di diversi tipi.

    @GetMapping
    public List<AuthorDTO> getAuthor() { //Gestisce la richiesta GET su "/author" e restituisce una lista di oggetti DTO ottenuti
                                        // convertendo gli oggetti Author restituiti dal servizio AuthorService.
        return authorService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @GetMapping("/{id}") // Gestisce la richiesta GET su "/author/{id}", dove {id} è un parametro dinamico che rappresenta l'ID dell'autore.
    // Restituisce un oggetto AuthorDTO per l'autore corrispondente all'ID specificato.
    public AuthorDTO getAuthorById(@PathVariable long id) {
        return convertToDTO(authorService.getById(id));
    }

    @PostMapping  // Gestisce la richiesta POST su "/author" per creare un nuovo autore.
    // Prende un oggetto AuthorDTO come input, lo converte in un oggetto Author,
    // lo salva utilizzando il servizio AuthorService e restituisce l'oggetto AuthorDTO corrispondente.
    public AuthorDTO creatAuthor(@RequestBody AuthorDTO newAuthor){
        Author author= convertToEntity(newAuthor);
        author= authorService.create(author);
        return convertToDTO(author);
    }

    @PutMapping("/{id}") // Gestisce la richiesta PUT su "/author/{id}", dove {id} è l'ID dell'autore da aggiornare.
    // Prende un oggetto AuthorDTO come input, lo converte in un oggetto Author,
    // aggiorna l'autore corrispondente utilizzando il servizio AuthorService e restituisce l'oggetto AuthorDTO aggiornato.
    public AuthorDTO updateAuthor(@PathVariable long id, @RequestBody AuthorDTO updateAuthor) {
        Author author = convertToEntity(updateAuthor);
        author= authorService.update(id, author);
        return convertToDTO(author);
    }


    @DeleteMapping("/{id}") //Gestisce la richiesta DELETE su "/author/{id}", dove {id} è l'ID dell'autore da eliminare.
    // Recupera l'autore corrispondente utilizzando il servizio AuthorService,
    // elimina tutti i suoi libri utilizzando il servizio BookService,
    // elimina infine l'autore stesso e restituisce l'oggetto AuthorDTO corrispondente all'autore eliminato.
    public AuthorDTO deleteAuthor (@PathVariable long id) {
        Author author = authorService.getById(id);

        author.getBooks()
                .forEach(books -> bookService.delete(books.getId()));
        return convertToDTO(authorService.delete(id));
    }


    @DeleteMapping("/{id}/books") //
    public List<BookDTO> deleteBooks(@PathVariable long id){
        Author author= authorService.getById(id);

        List<BookDTO> bookDTO = author.getBooks()
                .stream()
                .map(books -> convertToBooksDTO(bookService.delete(books.getId())))
                .toList();

        return null;
    }

     @GetMapping("/{id}/books")
     public List<BookDTO> getBooks(@PathVariable long id) {
        Author author= authorService.getById(id);

    return author.getBooks()
            .stream()
            .map(this::convertToBooksDTO)
            .toList();
}

    private AuthorDTO convertToDTO(Author author) { //
        return modelMapper.map(author, AuthorDTO.class);
//Converte un oggetto Author in un oggetto AuthorDTO utilizzando il ModelMapper.
    }

    private Author convertToEntity(AuthorDTO dto){
        return modelMapper.map(dto, Author.class);
        // Converte un oggetto AuthorDTO in un oggetto Author utilizzando il ModelMapper.
    }

    private BookDTO convertToBooksDTO(Books books){
        return modelMapper.map(books, BookDTO.class);
        // convertToBooksDTO(): Converte un oggetto Books (presumibilmente l'entità dei libri) in un oggetto BookDTO utilizzando il ModelMapper.
    }



}
