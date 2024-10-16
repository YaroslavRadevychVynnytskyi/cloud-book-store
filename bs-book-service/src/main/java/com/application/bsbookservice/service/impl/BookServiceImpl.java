package com.application.bsbookservice.service.impl;

import com.application.bsbookservice.dto.book.BookDto;
import com.application.bsbookservice.dto.book.BookSearchParameters;
import com.application.bsbookservice.dto.book.CreateBookRequestDto;
import com.application.bsbookservice.dto.category.CategoryByIdsRequestDto;
import com.application.bsbookservice.mapper.BookMapper;
import com.application.bsbookservice.model.Book;
import com.application.bsbookservice.repo.BookRepository;
import com.application.bsbookservice.repo.SpecificationBuilder;
import com.application.bsbookservice.service.BookService;
import com.application.bsbookservice.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final SpecificationBuilder<Book> specificationBuilder;
    private final CategoryService categoryService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final Logger logger;

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(this::convertToBookDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = getBookFromDb(bookId);
        return convertToBookDto(book);
    }

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toModel(bookDto);

        Book savedBook = bookRepository.save(book);
        return convertToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(CreateBookRequestDto bookDto, Long bookId) {
        Book book = getBookFromDb(bookId);
        bookMapper.updateBookFromCreateBookRequestDto(book, bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToBookDto(savedBook);
    }

    @Override
    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> search(BookSearchParameters searchParams, Pageable pageable) {
        Page<Book> books = bookRepository
                .findAll(specificationBuilder.build(searchParams), pageable);
        return books.stream()
                .map(this::convertToBookDto)
                .toList();
    }

    private BookDto convertToBookDto(Book book) {
        logger.info("BookServiceImpl.convertToBookDto invoked");

        BookDto bookDto = bookMapper.toDto(book);

        CategoryByIdsRequestDto requestDto = new CategoryByIdsRequestDto(book.getCategoryIds());
        bookDto.setCategories(categoryService.getCategoryDetailsByIds(requestDto));
        return bookDto;
    }

    private Book getBookFromDb(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException("Book with ID: " + bookId + " not found"));
    }
}
