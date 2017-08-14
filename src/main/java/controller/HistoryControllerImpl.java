package controller;

import model.HistoryItem;
import model.Result;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eglushchenko on 11.08.2017.
 */
@WebServlet(name = "historyController", urlPatterns = {"/history"})
public class HistoryControllerImpl extends HttpServlet implements HistoryController {

    private List<HistoryItem> history;

    @Override
    public void init() throws ServletException {
        super.init();

        history = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<Result> result = Optional.ofNullable((Result) getServletContext().getAttribute("result"));
        result.ifPresent(this::updateWithResult);
        JSONArray jsonArr = new JSONArray();
        jsonArr.addAll(history.stream().map(HistoryItem::toJson).collect(Collectors.toList()));
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonArr.toJSONString());

    }

    @Override
    public void updateWithResult(Result result) {
        int id = history.size() + 1;
        Date date = new Date();
        String gender = "Male";
        int age = 10;
        float height = 180;
        float weight = 60;
        float bmi = result.getBmi();
        float pi = result.getPi();
        String kind = result.getKind();

        history.add(new HistoryItem(id, date, age, gender, height, weight, bmi, pi, kind));
    }
}
