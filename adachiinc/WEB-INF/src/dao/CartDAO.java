package dao;

import bean.Item;
import bean.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.servlet.http.*;


public class CartDAO extends DAO{
  public boolean insert(Item cart)throws Exception{
    Connection con = getConnection();

    con.setAutoCommit(false);

      PreparedStatement st = con.prepareStatement(
      "insert into cart values(?,null,?,?,?,?)");
      st.setString(1,cart.getBuyer());
      st.setString(2,cart.getSeller());
      st.setString(3,cart.getName());
      st.setInt(4,cart.getPrice());
      st.setInt(5,cart.getQuantity());
      int line = st.executeUpdate();
      st.close();

      if(line != 1){
        con.rollback();
        con.setAutoCommit(true);
        con.close();

      return false;
      }

    con.commit();
    con.setAutoCommit(true);
    con.close();
    return true;
  }

  public List<Item> search(String keyword)throws Exception{
    Connection con = getConnection();
    PreparedStatement st = con.prepareStatement(
    "select * from cart where buyer = ?");
    st.setString(1,keyword);
    ResultSet rs = st.executeQuery();
    List<Item> cart = new ArrayList<>();

    while(rs.next()){
      Item i = new Item();
      i.setId(rs.getInt("id"));
      i.setBuyer(rs.getString("buyer"));
      i.setSeller(rs.getString("username"));
      i.setName(rs.getString("product_name"));
      i.setPrice(rs.getInt("product_price"));
      i.setQuantity(rs.getInt("product_quantity"));
      cart.add(i);
    }
    st.close();
    con.close();

    return cart;
  }

    public int search(String name,int id)throws Exception{
      Connection con = getConnection();
      PreparedStatement st = con.prepareStatement(
      "select * from cart where product_name like ?");
      st.setString(1,name);
      ResultSet rs = st.executeQuery();
      List<Item> cart = new ArrayList<>();
      int checker =0;
      while(rs.next()){
        checker ++;
      }
      st.close();
      con.close();

      return checker;
    }

    public int update(int quantity,String name) throws Exception{
      Connection con = getConnection();
      PreparedStatement st = con.prepareStatement(
      "update cart set product_quantity = product_quantity + ? where product_name=?");

      st.setInt(1,quantity);
      st.setString(2,name);
      int num = st.executeUpdate();

      st.close();
      con.close();
      return num;
    }

  public int drop(int id) throws Exception{
    Connection con = getConnection();
    PreparedStatement st = con.prepareStatement("delete from cart where id=?");
    st.setInt(1, id);
    int log = st.executeUpdate();

    st.close();
    con.close();
    return log;
  }
  public List<Item> search2(int id) throws Exception{
    List<Item> line = new ArrayList<>();
    Connection con = getConnection();
    PreparedStatement st = con.prepareStatement("select * from cart where id = ?");
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();

    while(rs.next()){
      Item i = new Item();
      i.setPrice(rs.getInt("product_price"));
      i.setQuantity(rs.getInt("product_quantity"));
      line.add(i);
    }
    st.close();
    con.close();
    return line;
  }
}
