package menu;

import java.util.List;
import java.util.ArrayList;
import bean.User;
import bean.Item;
import tool.Action;
import dao.ProductDAO;
import javax.servlet.http.*;
import java.io.*;
import dao.CalcDAO;

public class CartHistoryAction extends Action{
  public String execute(
  HttpServletRequest request, HttpServletResponse response
  )throws Exception{
    HttpSession session = request.getSession();

    User user = (User)session.getAttribute("user");
    String username = user.getName();
    String names[] = request.getParameterValues("name");
    session.setAttribute("names", names);
    int pay[] = {0, 0, 0, 0};
    int income[] = {0, 0, 0, 0};
    int money = 0;
    String message[] = {"", "", "", ""};
    List<Item> sell_list = null;
    List<Item> buy_list = null;
    int index = 0;

    //自分が買ったもの
    CalcDAO dao = new CalcDAO();
    for(String n : names){
    sell_list = dao.sellerSearch(username, names);
    for(Item i : sell_list){

      pay[index] += i.getPrice() * i.getQuantity();

  }


    //自分が売ったもの
    buy_list = dao.buyerSearch(names, username);

    for(Item item : buy_list){

      income[index] += item.getPrice() * item.getQuantity();

}
money=pay[index]-income[index];
message[index] = "からの" + money + "円の収入です。";

index++;

}


  /*  if(pay > income){
      money = pay - income;
      message = "への" + money + "円のお支払いです。";


    }else if(income > pay){
      money = income - pay;
      message = "からの" + money + "円の収入です。";


    }else if(pay == income){
      message = "相殺されています。";

    }*/



    session.setAttribute("sell_list", sell_list);
    session.setAttribute("buy_list", buy_list);
    session.setAttribute("message", message);
    List<User> userslist = dao.usersSearch();
    session.setAttribute("userslist", userslist);

   return "calc.jsp";
  }

}
