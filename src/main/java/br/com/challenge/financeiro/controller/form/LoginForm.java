package br.com.challenge.financeiro.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotBlank @Email
	private String email;
	@NotBlank
	private String senha;
	
	
	
	
	
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public UsernamePasswordAuthenticationToken converter() {
		// TODO Auto-generated method stub
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
	
	
	

}
