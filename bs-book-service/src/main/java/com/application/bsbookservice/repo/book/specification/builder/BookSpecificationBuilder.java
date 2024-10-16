package com.application.bsbookservice.repo.book.specification.builder;

import com.application.bsbookservice.dto.book.BookSearchParameters;
import com.application.bsbookservice.model.Book;
import com.application.bsbookservice.repo.SpecificationBuilder;
import com.application.bsbookservice.repo.SpecificationProviderManager;
import com.application.bsbookservice.repo.book.specification.provider.PriceSpecificationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book, ? super Object> bookSpecificationProviderManager;
    private final PriceSpecificationProvider priceSpecProvider;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.authors()));
        }
        if (searchParameters.minPrice() != null || searchParameters.maxPrice() != 0) {
            spec = spec.and(priceSpecProvider.getSpecification(searchParameters.minPrice(),
                    searchParameters.maxPrice()));
        }
        return spec;
    }
}
