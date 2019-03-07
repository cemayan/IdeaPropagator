package com.example.api.repository;

import com.example.api.model.SharedItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SharedItemRepository extends ReactiveMongoRepository<SharedItem, String> {

}
