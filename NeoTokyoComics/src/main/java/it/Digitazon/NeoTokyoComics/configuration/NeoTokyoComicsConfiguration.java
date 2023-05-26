package it.Digitazon.NeoTokyoComics.configuration;

import it.Digitazon.NeoTokyoComics.persistence.entities.Author;
import it.Digitazon.NeoTokyoComics.presentation.dto.AuthorDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeoTokyoComicsConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Author, AuthorDTO> propretaryMapper= modelMapper.createTypeMap(Author.class, AuthorDTO.class);
        propretaryMapper.addMapping(Author::getBirthDate, AuthorDTO::convertDateToString);
        TypeMap<AuthorDTO, Author> propertyMapperDTO = modelMapper.createTypeMap(AuthorDTO.class, Author.class);
        propertyMapperDTO.addMapping(AuthorDTO::convertBirthDate, Author::setBirthDate );
        return modelMapper;
    }
}
