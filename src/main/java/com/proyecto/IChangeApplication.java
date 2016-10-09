package com.proyecto;

import com.proyecto.DAO.ArticleDAO;
import com.proyecto.DAO.ExchangeDAO;
import com.proyecto.DAO.UserDAO;
import com.proyecto.domain.Article;
import com.proyecto.domain.Exchange;
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

	@Autowired
	private ExchangeDAO exchangeDAO;

	@Bean
	CommandLineRunner runner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				//Buscar todos los usuarios.
				Iterable<User> users = userDAO.findAll();
				users.forEach(u -> System.out.println("id:"+u.getIdUser() + " Nombre: "+u.getUserName()));

				//Buscar un usuario por nickname
				User user = userDAO.findOne("mery");
				System.out.println("Usuario Encontrado: "+ user.getUserName());

				// Buscar todos los artículos
				Iterable<Article> articles = articleDAO.findAll();
				articles.forEach(n -> System.out.println( "Artículo: "+n.getName()));

				//Buscar los artículos de un usuario en específico.
				Iterable<Article> user_art = articleDAO.findAllFromUser(2);
				user_art.forEach(n-> System.out.println(n.getName()));

				//Borrar un usuario en específico
				userDAO.deleteUser(user);
				System.out.println("Usuario borrado: "+ user.getUserName());

				// imaginemos que se ha producido un intercambio y lo queremos puntuar
				User user2 = userDAO.findOne("josi");
				Exchange exchange = new Exchange();
				exchange.setDone(true);
				exchangeDAO.evaluateExchange(5, user2,exchange );//debería de tener guardado 2'5

				User user3 = userDAO.findOne("Jota");
				exchangeDAO.evaluateExchange(3,user3 , exchange);//debería de tener guardado 3

				User user5 = userDAO.findOne("jordinho");
				exchangeDAO.evaluateExchange(5,user5 , exchange);//3'5

				//Hacer intercambio entre 2 usuarios
				//usuario con id = 1 posee el artículo libro Harry Potter
				//usuario con id = 2 posee el artículo vestido vintage

				exchangeDAO.executeExchange(user2, 2, user5, 1);

			}
		};
	}
}
