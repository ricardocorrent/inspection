package br.com.inspection.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, UUID> {

    @Query("select t from Tag t where t.title like lower(concat ('%', :title, '%'))")
    Page<Tag> findTagByTitle(@Param("title") final String title, final Pageable pageable);
}
