package dao;

import bean.Item;
import bean.Product;
import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.servlet.http.*;
//username出品者 buyer購入者
public class CalcDAO extends DAO{
  //自分が売ったもの
  public List<Item> buyerSearch(String[] name, String username) throws Exception{

    Connection con = getConnection();
    List<Item> list = new ArrayList<>();
    PreparedStatement st = null;
    for(String n : name){
    st = con.prepareStatement("select * from cart where buyer like ? and username like ?");
    st.setString(1, n);
    st.setString(2, username);

    ResultSet rs = st.executeQuery();

    while(rs.next()){
      Item i = new Item();
      i.setBuyer(rs.getString("buyer"));
      i.setSeller(rs.getString("username"));
      i.setName(rs.getString("product_name"));
      i.setPrice(rs.getInt("product_price"));
      i.setQuantity(rs.getInt("product_quantity"));
      list.add(i);
    }
  }
    st.close();
    con.close();
    return list;
  }
  //自分が買ったもの
  public List<Item> sellerSearch(String username, String[] name) throws Exception{

    Connection con = getConnection();
    List<Item> list2 = new ArrayList<>();
    PreparedStatement st=null;
      for(String n : name){
    st = con.prepareStatement("select * from cart where buyer like ? and username like ?");
    st.setString(1, username);
    st.setString(2, n);
    ResultSet rs = st.executeQuery();

    while(rs.next()){
      Item i = new Item();
      i.setBuyer(rs.getString("buyer"));
      i.setSeller(rs.getString("username"));
      i.setName(rs.getString("product_name"));
      i.setPrice(rs.getInt("product_price"));
      i.setQuantity(rs.getInt("product_quantity"));
      list2.add(i);
    }

  }
    st.close();
    con.close();
    return list2;
  }

 public List<User> usersSearch() throws Exception{
   Connection con = getConnection();
   List<User> list = new ArrayList<>();
   PreparedStatement st = con.prepareStatement("select * from user");
   ResultSet rs = st.executeQuery();

   while(rs.next()){
     User u = new User();
     u.setName(rs.getString("name"));
     list.add(u);
   }
   st.close();
   con.close();
   return list;
 }


  }
