package br.com.inspection.item;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {

    @Query("SELECT i FROM Item i WHERE i.rule.id =:ruleId")
    List<Item> findByRuleId(@Param("ruleId") UUID ruleId);
}
