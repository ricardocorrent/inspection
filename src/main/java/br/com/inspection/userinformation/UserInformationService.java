package br.com.inspection.userinformation;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserInformationService {

    @Inject
    private UserInformationRepository userInformationRepository;
}
