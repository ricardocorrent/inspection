package br.com.inspection.rule;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RuleRepository extends PagingAndSortingRepository<Rule, UUID> {
}
