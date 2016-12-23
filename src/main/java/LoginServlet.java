@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    req.getRequestDispatcher("/login.jsp").forward(req, resp);
  }


  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String username = req.getParameter("username");
    String passwd = req.getParameter("password");
    try {
      AVUser.logIn(username, passwd);
      req.getRequestDispatcher("/profile").forward(req, resp);
    } catch (AVException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      resp.setContentType("application/json; charset=UTF-8");
      JSONObject result = new JSONObject();
      result.put("code", e.getCode());
      result.put("error", e.getMessage());
      resp.getWriter().write(result.toJSONString());
      e.printStackTrace();
    }
  }
}