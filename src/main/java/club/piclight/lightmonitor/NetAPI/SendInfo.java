package club.piclight.lightmonitor.NetAPI;

import club.piclight.lightmonitor.Bean.ClientBean;
import club.piclight.lightmonitor.DAO.ClientsDAO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/sendinfo")
public class SendInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String userSession = null;
        String info = null;
        int id;
        userSession = req.getParameter("usersession");
        info = req.getParameter("info");
        if (userSession != null && info != null) {
            id = sessionGetId(userSession);
            if (id != -1) {
                ClientsDAO.getClientsDao().infoClient(id, info);
                resp.setStatus(200);
                resp.setContentType("text/plain");

                ServletOutputStream out = resp.getOutputStream();
                out.println("OK!Set client info");
            } else {
                resp.setStatus(400);
                resp.setContentType("text/plain");

                ServletOutputStream out = resp.getOutputStream();
                out.println("Error! User No found.");
            }
        } else {
            resp.setStatus(400);
            resp.setContentType("text/plain");

            ServletOutputStream out = resp.getOutputStream();
            out.println("Error! Please Check Your Parameter");
        }
    }

    /**
     * Use Usersession to search user id TODO: Refactor this repeat method to DAO
     *
     * @param session
     * @return
     */
    private int sessionGetId(String session) {
        ArrayList<ClientBean> clientBeans = ClientsDAO.getClientsDao().getClientList();
        for (ClientBean client : clientBeans) {
            if (session.equals(client.getUserSession()))
                return client.getId();
        }
        return -1; //Can't found user by this session
    }
}
