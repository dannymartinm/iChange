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
				Iterable<User> users = userDAO.findAll();
				users.forEach(u -> System.out.println("id:"+u.getId() + " Nombre: "+u.getUserName()));

				User user = userDAO.findOne("Maria");
				System.out.println("Usuario Encontrado: "+ user.getUserName());

				Iterable<Article> articles = articleDAO.findAll();
				articles.forEach(n -> System.out.println( "Artículo: "+n.getName()));

			}
		};
	}
}
