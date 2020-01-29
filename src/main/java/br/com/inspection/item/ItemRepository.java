package br.com.inspection.item;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {
}
