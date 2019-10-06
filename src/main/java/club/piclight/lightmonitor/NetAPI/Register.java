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

/**
 * Get HTTP Get request from client, and register a new client
 */
@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set HTTP Encoding method
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String clientName = null;

        //Get Parameter from request
        clientName = req.getParameter("clientname");

        if (clientName != null) {
            ClientBean client = register(clientName); //Register new client

            //Generate a response body instance
            ResponseBody responseBody = new ResponseBody(200, "OK", client.getUserSession(), client.getId());

            //Serialize responseBody instance to JSON text
            String respJSONText = new Gson().toJson(responseBody);

            //Set response head
            resp.setStatus(200);
            resp.setContentType("application/json");

            //Output response
            ServletOutputStream out = resp.getOutputStream();
            out.println(respJSONText);
            out.flush();
            out.close();
        } else { //Parameter illegal, response 400
            resp.setStatus(400);
            resp.setContentType("text/plain");

            //Output response
            ServletOutputStream out = resp.getOutputStream();
            out.println("Error! Please Check Your Parameter");
        }
    }

    private ClientBean register(String clientName) {
        //Generate a random uuid as usersession
        String userSession = UUID.randomUUID().toString();

        //New and set a ClientBean instance
        ClientBean clientBean = new ClientBean();
        clientBean.setClientName(clientName);
        clientBean.setLastestOnline(Calendar.getInstance());
        clientBean.setUserSession(userSession);

        //Register ClientBean on ClientsDAO
        int id = ClientsDAO.getClientsDao().registerClient(clientBean);
        return ClientsDAO.getClientsDao().getClientById(id);
    }

    /**
     * ResponseBody Inner class, use to generate JSON by Gson
     */
    private class ResponseBody {
        private int code;
        private String message;
        private String usersession;
        private int id;

        ResponseBody(int code, String message, String usersession, int id) {
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
