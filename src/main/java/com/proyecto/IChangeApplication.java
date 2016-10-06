package com.proyecto;

import com.proyecto.DAO.ArticleDAO;
import com.proyecto.DAO.UserDAO;
import com.proyecto.domain.Article;
import com.proyecto.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IChangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IChangeApplication.class, args);
	}

	@Autowired
	private ArticleDAO articleDAO;

	@Autowired
	private UserDAO userDAO;

	@Bean
	CommandLineRunner runner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				//Buscar todos los usuarios.
				Iterable<User> users = userDAO.findAll();
				users.forEach(u -> System.out.println("id:"+u.getId() + " Nombre: "+u.getUserName()));

				//Buscar un usuario por nombre
				User user = userDAO.findOne("mery");
				System.out.println("Usuario Encontrado: "+ user.getUserName());

				// Buscar todos los artículos
				Iterable<Article> articles = articleDAO.findAll();
				articles.forEach(n -> System.out.println( "Artículo: "+n.getName()));

				//Buscar los artículos de un usuario en específico.
				Iterable<Article> user_art = articleDAO.findAllFromUser(2);
				user_art.forEach(n-> System.out.println(n.getName()));


			}
		};
	}
}
