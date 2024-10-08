package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public User getUserByCar(String model, int series) {
      String hql = "FROM User where car.model = :paramModel AND car.series = :paramSeries";
      Query<?> query = userDao.getSessionFactory().getCurrentSession().createQuery(hql);
      query.setParameter("paramModel", model);
      query.setParameter("paramSeries", series);
      List<User> users = (List<User>) query.list();
      return users.get(0);
   }
}
