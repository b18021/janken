package oit.is.z1631.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users")
  ArrayList<User> selectAllUsers();

  @Select("SELECT id from users")
  ArrayList<User> selectid();

  @Select("SELECT name from users")
  ArrayList<User> selectname();

}
