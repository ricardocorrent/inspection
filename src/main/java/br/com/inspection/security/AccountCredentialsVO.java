package br.com.inspection.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class AccountCredentialsVO implements Serializable {

    private String userName;

    private String password;

}