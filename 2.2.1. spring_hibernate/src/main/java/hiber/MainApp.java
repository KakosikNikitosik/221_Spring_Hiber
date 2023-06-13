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
      userService.add(new User("Noname", "Noname", "user1@mail.ru"), new Car("mark", 2));
      userService.add(new User("Arkadiy", "Car'egradcev", "failcrew@mail.ru"), new Car("z", 370));
      userService.add(new User("Dominic", "Toretto", "WhoSaidAboutFamily@mail.ru"), new Car("Charger", 68));
      userService.add(new User("Жена", "Domonic'a", "WhoSaidAboutFamilyToo@mail.ru"), new Car("Charger", 68));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+ user.getCar().getModel());
         System.out.println("Series = "+ user.getCar().getSeries());
         System.out.println();
      }

      List<Car> usersCheckingByCars = userService.getUserByCar("Charger", 68);
      for (Car car : usersCheckingByCars){

         System.out.println("AUTOMOBILE = "+car.getModel()+" "+ car.getSeries());
         System.out.println("Id = "+car.getUser().getId());
         System.out.println("First Name = "+car.getUser().getFirstName());
         System.out.println("Last Name = "+car.getUser().getLastName());
         System.out.println("Email = "+car.getUser().getEmail());
         System.out.println();
      }

      context.close();
   }
}
