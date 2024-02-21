package com.example.GraphqlDgs.DataFetchers;

import com.example.GraphqlDgs.Exceptions.UserAlreadyExistsException;
import com.example.GraphqlDgs.Exceptions.UserNotFoundException;
import com.example.GraphqlDgs.Repository.UserRepository;
import com.example.GraphqlDgs.model.User;
import com.netflix.graphql.dgs.*;

import java.util.*;

@DgsComponent
public class UsersDataFetcher {
    UserRepository repo;
    UsersDataFetcher(UserRepository repo){
        this.repo=repo;
    }
    @DgsQuery
    public List<User> users() {
        return repo.findAll();
    }
    @DgsMutation
    public User addUser(@InputArgument String name,@InputArgument long id,@InputArgument String email,@InputArgument String password){
        User u=new User(name,id,email,password);
        if(repo.existsById(id))throw new UserAlreadyExistsException();
        return repo.save(u);
    }

    @DgsMutation
    public Boolean delUser(@InputArgument long id){
        if(!repo.existsById(id))throw new UserNotFoundException();
        repo.deleteById(id);
        return true;
    }
    @DgsQuery
    public User user(@InputArgument long id){
        if(!repo.existsById(id))throw new UserNotFoundException();
        return repo.getById(id);
    }
    @DgsMutation
    public User updUser(@InputArgument String name,@InputArgument long id,@InputArgument String email,@InputArgument String password){
        User u=new User(name,id,email,password);
        if(!repo.existsById(id)) throw new UserNotFoundException();
        repo.save(u);
        return repo.getById(id);
    }
}
