package it.Digitazon.NeoTokyoComics.persistence.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "types")
public class Type {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(name = "name", nullable = false)
 private String name;
 @Column(name = "isDeleted", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT false")
 private boolean isDeleted;
 @OneToMany(mappedBy = "type")
 private List<Books> books;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public boolean isDeleted() {
  return isDeleted;
 }

 public void setDeleted(boolean deleted) {
  isDeleted = deleted;
 }

 public List<Books> getBooks() {
  return books;
 }

 public void setBooks(List<Books> books) {
  this.books = books;
 }
}
