package com.example.restfull.RestFullApis.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static List<User> userList = new ArrayList<>();
	private static int countUser=0;
	
	static {
		userList.add(new User(++countUser,"Rohit",LocalDate.now().minusYears(25)));
		userList.add(new User(++countUser,"Shantnu",LocalDate.now().minusYears(23)));
		userList.add(new User(++countUser,"Omkar",LocalDate.now().minusYears(25)));
		userList.add(new User(++countUser,"Suraj",LocalDate.now().minusYears(24)));
	}
	
	public List<User> getAllUsers(){
		return userList;
	}

	public User getUserById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		return userList.stream().filter(predicate ).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++countUser);
		userList.add(user);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		userList.removeIf(predicate);
	}
	
}
