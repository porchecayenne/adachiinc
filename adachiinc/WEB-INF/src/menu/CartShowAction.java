package menu;

import tool.Action;
import bean.User;
import bean.Item;
import dao.CartDAO;
import java.util.List;
import javax.servlet.http.*;


public class CartShowAction extends Action{
  static int total = 0;
  public String execute(
    HttpServletRequest request, HttpServletResponse response
  )throws Exception{
    HttpSession session = request.getSession();
    User user = (User)session.getAttribute("user");
    String keyword =  "";
    if(user!=null) keyword =  user.getName();
    CartDAO dao = new CartDAO();
    List<Item> cart = dao.search(keyword);

    session.setAttribute("cart",cart);
    for(Item i : cart){
      total += i.getPrice()*i.getQuantity();
      session.setAttribute("total", total);
    }


    return "cart.jsp";
  }
}
