package br.com.inspection.inspection;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InspectionRepository extends PagingAndSortingRepository<Inspection, UUID> {
}
