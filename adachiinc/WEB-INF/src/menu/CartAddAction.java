package menu;

import bean.Item;
import bean.Product;
import bean.User;
import dao.CartDAO;
import dao.ProductDAO;
import tool.Action;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

public class CartAddAction extends Action{
  static int total = 0;
  @SuppressWarnings("unchecked")

  public String execute(
      HttpServletRequest request, HttpServletResponse response
  )throws Exception{

        HttpSession session = request.getSession();
        User user  = (User)session.getAttribute("user");
        String username = user.getName();
        String seller = request.getParameter("SELLER");
        String name = request.getParameter("NAME");
        int price = Integer.parseInt(request.getParameter("PRICE"));
        int quantity = Integer.parseInt(request.getParameter("counter"));
        int id = Integer.parseInt(request.getParameter("ID"));


              Item cart = new Item();
              cart.setBuyer(username);
              cart.setSeller(seller);
              cart.setName(name);
              cart.setPrice(price);
              cart.setQuantity(quantity);

        CartDAO dao =  new CartDAO();
          int checker = dao.search(name,id);
              if(checker >= 1){
                dao.update(quantity,name);
              }else{
                dao.insert(cart);
              }

        ProductDAO dao2 = new ProductDAO();
        dao2.update(quantity,id);

return "Product.action";
        }

  }
