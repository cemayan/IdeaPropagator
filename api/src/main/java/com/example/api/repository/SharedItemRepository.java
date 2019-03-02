package com.example.api.repository;

import com.example.api.model.SharedItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SharedItemRepository extends PagingAndSortingRepository<SharedItem,String> {
}
