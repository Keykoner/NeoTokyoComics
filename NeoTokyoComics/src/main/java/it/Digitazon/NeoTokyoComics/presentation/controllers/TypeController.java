package it.Digitazon.NeoTokyoComics.presentation.controllers;

import it.Digitazon.NeoTokyoComics.persistence.entities.Books;
import it.Digitazon.NeoTokyoComics.persistence.entities.Type;
import it.Digitazon.NeoTokyoComics.presentation.dto.BookDTO;
import it.Digitazon.NeoTokyoComics.presentation.dto.TypeDTO;
import it.Digitazon.NeoTokyoComics.service.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeController {


    @Autowired
    private TypeService typeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TypeDTO> getTypes() {
        return typeService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @GetMapping("/{id}")
    public TypeDTO getTypeById(@PathVariable long id) {
        return convertToDTO(typeService.getById(id));
    }

    @PostMapping
    public TypeDTO createType(@RequestBody TypeDTO newType) {
        Type type = convertToEntity(newType);
        type = typeService.create(type);
        return convertToDTO(type);
    }

    @PutMapping("/{id}")
    public TypeDTO updateType(@PathVariable long id, @RequestBody TypeDTO updateType) {
        Type type = convertToEntity(updateType);
        type = typeService.update(id, type);
        return convertToDTO(type);
    }

    @DeleteMapping("/{id}")
    public TypeDTO deleteType(@PathVariable long id) {
        return convertToDTO(typeService.delete(id));
    }

    @GetMapping("/{id}/books")
    public List<BookDTO> getBooks(@PathVariable long id){
        Type type = typeService.getById(id);
        return type.getBooks()
                .stream()
                .map(this::convertToBookDTO)
                .toList();
    }


    private TypeDTO convertToDTO(Type type) {
        return modelMapper.map(type, TypeDTO.class);
    }

    private Type convertToEntity(TypeDTO dto) {
        return modelMapper.map(dto, Type.class);

    }


    private BookDTO convertToBookDTO(Books books){
        return modelMapper.map(books, BookDTO.class);
    }

}
