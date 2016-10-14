package com.proyecto;

import com.proyecto.DAO.ArticleDAO;
import com.proyecto.DAO.CategoryDAO;
import com.proyecto.DAO.ExchangeDAO;
import com.proyecto.DAO.UserDAO;
import com.proyecto.domain.Article;
import com.proyecto.domain.Category;
import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Set;
import java.util.HashSet;

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

    @Autowired
    private CategoryDAO categoryDAO;

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
				User user3 = userDAO.findOne("Jota");
				User user5 = userDAO.findOne("jordinho");

				//Editar un usuario
				user3.setUserName("hola");
				user3.setZone("bcn");
				userDAO.updateUser(user3);

				//Editar un artículo.
				Article article = articleDAO.findOne(1);
				article.setName("editandoartículo");
				article.setQuantity(22);
				articleDAO.updateArticle(article);

                //Listar las categorías.
                Iterable<Category> categories = categoryDAO.findAll();
                categories.forEach(u -> System.out.println("id:"+u.getIdCategory() + " Nombre: "+u.getNameCategory()+" Description: "+u.getDescription()));

				//Borrar categoría
				Category categoria = categoryDAO.findOne(1);
				categoryDAO.deleteOneCategory(categoria);

				//Editar categoría
				Category categoria2= categoryDAO.findOne(2);
				categoria2.setNameCategory("Editando Categoría");
				categoryDAO.updateCategory(categoria2);


				//Hacer un exchange
				User jordi= userDAO.findOne("jordinho");
				User josep= userDAO.findOne("josi");
				Article article1= articleDAO.findOne(1);
				Article article2= articleDAO.findOne(2);

				exchangeDAO.executeExchange(josep,article1,jordi,article2, "Barcelona");

				//Hacer otro exchange
				User merce= userDAO.findOne("merc");
				Article article3 =articleDAO.findOne(3);
				exchangeDAO.executeExchange(jordi,article2,merce,article3, "Valencia");

				//Artículo
				Article articulo= new Article();
				articulo.setName("nuevoarticulo");

				Set<Category> categoriesList = new HashSet<Category>();
				categoriesList.add(categoria);
				categoriesList.add(categoria2);

				articulo.setCategories(categoriesList);



				//articleDAO.save(articulo, user, exchangeDAO.);


			}

		};
	}
}