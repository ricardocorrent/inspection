package br.com.inspection.target;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TargetRepository extends PagingAndSortingRepository<Target, UUID> {

    @Query("select t from Target t where t.name like lower(concat ('%', :name, '%'))")
    Page<Target> findTargetByName(@Param("name") final String name, final Pageable pageable);

}
