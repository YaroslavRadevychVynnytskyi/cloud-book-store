package com.application.bsbookservice.service;

import com.application.bsbookservice.dto.book.BookDto;
import com.application.bsbookservice.dto.book.BookSearchParameters;
import com.application.bsbookservice.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long bookId);

    BookDto save(CreateBookRequestDto bookDto);

    BookDto updateBook(CreateBookRequestDto bookDto, Long bookId);

    void deleteById(Long bookId);

    List<BookDto> search(BookSearchParameters searchParams, Pageable pageable);
}
