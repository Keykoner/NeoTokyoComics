package it.Digitazon.NeoTokyoComics.presentation.controllers;

import it.Digitazon.NeoTokyoComics.persistence.entities.Author;
import it.Digitazon.NeoTokyoComics.persistence.entities.Books;
import it.Digitazon.NeoTokyoComics.persistence.entities.Type;
import it.Digitazon.NeoTokyoComics.presentation.dto.AuthorDTO;
import it.Digitazon.NeoTokyoComics.presentation.dto.BookDTO;
import it.Digitazon.NeoTokyoComics.presentation.dto.TypeDTO;
import it.Digitazon.NeoTokyoComics.service.AuthorService;
import it.Digitazon.NeoTokyoComics.service.BookService;
import it.Digitazon.NeoTokyoComics.service.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable long id){
        return convertToDTO(bookService.getById(id));
    }

    @PostMapping
    public BookDTO createBooks(@RequestBody BookDTO newBooks){
        Books books =convertToEntity(newBooks);
        books= bookService.create(books);

        return convertToDTO(books);
    }

    @PutMapping
    public BookDTO updateBooks(@PathVariable long id, @RequestBody BookDTO updateBooks){
        Books books= convertToEntity(updateBooks);
        books= bookService.update(id, books);
        return convertToDTO(books);
    }


    @DeleteMapping("/{id}")
    public BookDTO deleteBooks(@PathVariable long id){
        return convertToDTO(bookService.delete(id));
    }

    @GetMapping("/{id}/author")
    public AuthorDTO getAuthor(@PathVariable long id){
        Books books= bookService.getById(id);
        return convertToAuthorDTO(books.getAuthor());
    }







    private BookDTO convertToDTO(Books books) {
        return modelMapper.map(books, BookDTO.class);
    }

    private Books convertToEntity(BookDTO dto){
        Books books= modelMapper.map(dto, Books.class);

        Type bookType= typeService.getById(dto.getIdType());
        Author bookAuthor= authorService.getById(dto.getIdAuthor());

        books.setAuthor(bookAuthor);
        books.setType(bookType);

        return books;
    }


    private AuthorDTO convertToAuthorDTO(Author author){
        return modelMapper.map(author, AuthorDTO.class);
    }

    private TypeDTO convertToTypeDTO(Type type){
        return modelMapper.map(type, TypeDTO.class);
    }

}
