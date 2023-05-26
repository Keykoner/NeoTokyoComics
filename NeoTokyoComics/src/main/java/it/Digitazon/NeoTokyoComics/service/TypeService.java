package it.Digitazon.NeoTokyoComics.service;

import it.Digitazon.NeoTokyoComics.persistence.entities.Type;
import it.Digitazon.NeoTokyoComics.persistence.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getById(long id) {
        Optional<Type> optionalType = typeRepository.findById(id);
        if(optionalType.isEmpty()) {
            throw new IllegalStateException("Type not found");
        }
        return optionalType.get();
    }

    public Type create(Type newType) {
        newType = typeRepository.save(newType);

        return newType;
    }

    public Type update(long id, Type updateType) {
        Optional<Type> optionalType = typeRepository.findById(id);

        if(optionalType.isEmpty()){
            throw new IllegalStateException("Entity not found");
        }

        Type entityToUpdate= optionalType.get();
        entityToUpdate.setName(updateType.getName());
        entityToUpdate= typeRepository.save(entityToUpdate);
        updateType.setId(entityToUpdate.getId());
        return updateType;
    }

    public Type delete(long id) {
        Optional<Type> optionalType= typeRepository.findById(id);

        if (optionalType.isEmpty()){
            throw new IllegalStateException("Entity not found");
        }
        Type entityToDelete = optionalType.get();
        typeRepository.delete(entityToDelete);
        return entityToDelete;
    }




}
