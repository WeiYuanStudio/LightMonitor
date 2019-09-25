package club.piclight.lightmonitor.NetAPI;

import club.piclight.lightmonitor.Bean.ClientBean;
import club.piclight.lightmonitor.DAO.ClientsDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String clientName = null;
        clientName = req.getParameter("clientname");
        if (clientName != null) {
            ClientBean client = register(clientName);
            ResponseBody responseBody = new ResponseBody(200, "OK", client.getUserSession(), client.getId());

            resp.setStatus(200);
            resp.setContentType("application/json");

            ServletOutputStream out = resp.getOutputStream();
            out.println(new Gson().toJson(responseBody));
            out.flush();
            out.close();
        } else {
            ServletOutputStream out = resp.getOutputStream();
            resp.setStatus(400);
            resp.setContentType("text/plain");
            out.println("Error! Please Check Your Parameter");
        }
    }

    private ClientBean register(String clientName) {
        ClientBean clientBean = new ClientBean();
        clientBean.setClientName(clientName);
        clientBean.setLastestOnline(Calendar.getInstance());
        String session = UUID.randomUUID().toString();
        clientBean.setUserSession(session);
        int id = ClientsDAO.getClientsDao().registerClient(clientBean);
        return ClientsDAO.getClientsDao().getClientById(id);
    }

    private class ResponseBody {
        private int code;
        private String message;
        private String usersession;
        private int id;

        public ResponseBody(int code, String message, String usersession, int id) {
            this.code = code;
            this.message = message;
            this.usersession = usersession;
            this.id = id;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUsersession() {
            return usersession;
        }

        public void setUsersession(String usersession) {
            this.usersession = usersession;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
