package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll 
	void start() {
		usuarioRepository.save(new Usuario(0L, "Carla Dias", "calinha@email.com", "12345f6", "hhh"));
		usuarioRepository.save(new Usuario(0L, "Sarah Queiroz", "sarah@email.com", "1234f56", "hhh"));
		usuarioRepository.save(new Usuario(0L, "Pedro Dias", "pedro@email.com", "12345f6", "hhh"));
		
		
	}
	
	@Test 
	@DisplayName("Retorna 1 usuário")
	public void retornaUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("pedro@email.com");
		assertTrue(usuario.get().getUsuario().equals("pedro@email.com"));
		
		
	}
	
	@Test 
	@DisplayName("Retorna 2 usuários")
	public void retornaDoisUsuarios() {
		
		List<Usuario> listaDeUsuario = usuarioRepository.findAllByNomeContainingIgnoreCase ("Dias");
		assertEquals(2, listaDeUsuario.size());
		assertTrue(listaDeUsuario.get(0).getNome().equals("Carla Dias"));
		assertTrue(listaDeUsuario.get(2).getNome().equals("Pedro Dias"));
	
	}
	
	

}
