package github.com.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import github.com.meta.UserMeta;
import github.com.model.User;

public class UserService {

    public void regist(String name) {
        User user = new User();
        user.setName(name);
        Datastore.put(user);
    }
    public List<User> findAll() {
        UserMeta userMeta = UserMeta.get();
        return Datastore.query(userMeta).asList();
    }
    public User findByName(String name) {
        UserMeta userMeta = UserMeta.get();
        return Datastore.query(userMeta)
                .filter(userMeta.name.equal(name))
                .asSingle();
    }
    public void delete(String name) {
        User user = findByName(name);
        Datastore.delete(user.getKey());
    }
}
