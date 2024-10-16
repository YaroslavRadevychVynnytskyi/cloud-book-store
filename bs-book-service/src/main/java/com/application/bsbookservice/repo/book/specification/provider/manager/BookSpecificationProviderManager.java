package com.application.bsbookservice.repo.book.specification.provider.manager;

import com.application.bsbookservice.model.Book;
import com.application.bsbookservice.repo.SpecificationProvider;
import com.application.bsbookservice.repo.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager<R> implements SpecificationProviderManager<Book, R> {
    private final List<SpecificationProvider<Book, R>> specificationProviders;

    @Override
    public SpecificationProvider<Book, R> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(sp -> sp.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find correct specification "
                        + "provider for key: " + key));
    }
}
