package org.example.looam.web.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.example.looam.common.dto.CreatedResponse;
import org.example.looam.common.dto.PageResult;
import org.example.looam.web.client.book.dto.BookDTO;
import org.example.looam.web.client.book.dto.CreateBookCommand;
import org.example.looam.web.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookController {
  private final BookService bookService;

  @PostMapping
  public CreatedResponse createBook(@RequestBody @Valid CreateBookCommand command) {
    return bookService.createBook(command);
  }

  @PutMapping("/{id}/tags")
  public void updateBookTags(@PathVariable String id, @RequestBody List<@NotBlank String> tags) {
    bookService.updateBookTags(id, tags);
  }

  @PostMapping("/{id}/show")
  public void showBook(@PathVariable String id) {
    bookService.showBook(id);
  }

  @GetMapping("/{id}")
  public BookDTO findBook(@PathVariable String id) {
    return bookService.findById(id);
  }

  @GetMapping
  public PageResult<BookDTO> findBookPageable(
      @RequestParam(required = false) String searchKey,
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return bookService.findPage(searchKey, page, size);
  }

  @GetMapping("/first-ten")
  public List<BookDTO> findFirstTen(
      @RequestParam(required = false) String searchKey) {
    return bookService.findPage(searchKey, 1, 10).data();
  }

}
