package controller;

import model.History;
import model.HistoryItem;
import org.json.simple.JSONArray;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.Constants.HISTORY_ATTRIBUT_NAME;

/**
 * Created by eglushchenko on 11.08.2017.
 */
@WebServlet(name = "historyController", urlPatterns = {"/history"})
public class HistoryController extends HttpServlet {

    private History history;
    private ServletContext context;

    @Override
    public void init() throws ServletException {
        super.init();

        context = getServletContext();

        history = (History) context.getAttribute(HISTORY_ATTRIBUT_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<List<HistoryItem>> items = history.getAll();

        if (items.isPresent()) {
            JSONArray jsonArr = new JSONArray();
            JSONArray jsonArray = new JSONArray();
            jsonArr.addAll(items.get().stream().map(HistoryItem::toJson).collect(Collectors.toList()));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(jsonArr.toJSONString());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); //todo
        }
    }
}
