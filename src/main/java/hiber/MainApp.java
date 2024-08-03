package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Moscvich", 2141)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Niva", 21214)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Vaz", 2109)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Uaz", 469)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {//так как первые были добавлены без машин, запускал сначала без изменений
            System.out.println("Car = " + user.getCar().getModel() + user.getCar().getSeries());
         }
         System.out.println();
      }
      User u = userService.getUserByCar("Niva", 21214);
      System.out.println("user with specified model is:" + u.getLastName() + " " + u.getFirstName());
      User v = userService.getUserByCar("Uaz", 469);
      System.out.println("user with specified model is:" + v.getLastName() + " " + v.getFirstName());

      context.close();
   }
}
