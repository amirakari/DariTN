package tn.esprit.spring.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	//List<User> findByFirstName(String firstName);
	 Optional<User> findById(int id);
	//List<User> findByLastName(String lastName);
	//@Query("SELECT (MAX(e.id), 0) FROM Employee e")
	//Long getMaxId();
	@Query(nativeQuery = true, value ="select User FROM User f  where f.id=id")
    public User findUserByid(Long id);
	@Query("SELECT e FROM User e WHERE e.email=:email and e.password=:password")
	public User getUserByEmailAndPassword(@Param("email") String login, @Param("password") String password);


}
