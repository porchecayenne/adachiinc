package menu;

import bean.Item;
import tool.Action;
import dao.CartDAO;
import javax.servlet.http.*;
import java.util.List;
import menu.CartAddAction;

public class CartRemoveAction extends Action{
@SuppressWarnings("unchecked")
  public String execute(
  HttpServletRequest request, HttpServletResponse response
  )throws Exception{

HttpSession session=request.getSession();

int id=Integer.parseInt(request.getParameter("ID"));
List<Item> cart = (List<Item>)session.getAttribute("cart");
for(Item i : cart){
  if(i.getId()==id){
    CartAddAction.total -=i.getPrice()*i.getQuantity();
    session.setAttribute("total", CartAddAction.total);
    CartDAO dao = new CartDAO();
    dao.drop(id);
  }
}

return "CartShow.action";
}

}
