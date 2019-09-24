package br.com.inspection.userinformation;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInformationRepository extends PagingAndSortingRepository<UserInformation, UUID> {
}
