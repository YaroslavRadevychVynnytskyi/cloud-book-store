package com.application.bsbookservice.repo;

public interface SpecificationProviderManager<T, R> {
    SpecificationProvider<T, R> getSpecificationProvider(String key);
}
