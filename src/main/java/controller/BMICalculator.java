package controller;
/**
 * Created by genya on 11/08/2017.
 */

import model.History;
import model.HistoryItem;
import model.HistoryItemFactory;
import model.Result;
import sun.security.validator.ValidatorException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Math.pow;
import static util.Constants.*;
import static util.QueryUtil.*;

@WebServlet(name = "bmiCalculator", urlPatterns = {"/calculator"})
public class BMICalculator extends HttpServlet {

    private HistoryItemFactory historyItemFactory;

    @Override
    public void init() throws ServletException {
        historyItemFactory = new HistoryItemFactory();
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        float weight = Float.parseFloat(req.getParameter("weight"));
        float height = Float.parseFloat(req.getParameter("height"));
        float bmi = (float) (weight / pow(height / 100, 2));
        float pi = (float) (weight / pow(height / 100, 3));
        String kind = "Normal";
        Result result = new Result(bmi, pi, kind);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(result.toJson().toJSONString());

        ServletContext context = getServletContext();
        context.setAttribute("result", result);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String weightString = null;
        String heightString = null;
        String ageString = null;
        String gender = null;
        Float weight = null;
        Float height = null;
        Integer age = null;
        try {
            weightString = getField(req, QUERY_PARAM_WEIGH);
            heightString = getField(req, QUERY_PARAM_HEIGHT);
            ageString = getField(req, QUERY_PARAM_AGE);
            gender = getField(req, QUERY_PARAM_GENDER);

            weight = validateFloatStringField(weightString, QUERY_PARAM_WEIGH);
            height = validateFloatStringField(heightString, QUERY_PARAM_HEIGHT);
            age = validateIntStringField(ageString, QUERY_PARAM_AGE);
            gender = validateGender(gender, QUERY_PARAM_GENDER);
        } catch (ValidatorException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        float heightMiters = height / 100;

        float bmi = (float) (weight / pow(heightMiters, 2));
        float pi = (float) (height / pow(heightMiters, 3));
        String kind = null;

        if (age > 18) {
            if (bmi <= 16)
                kind = "Выраженный дефицит массы тела";
            else if (bmi <= 18.5)
                kind = "Недостаточная масса тела";
            else if (bmi <= 24.99)
                kind = "Норма";
            else if (bmi <= 30)
                kind = "Избыточная масса тела (предожирение)";
            else if (bmi <= 35)
                kind = "Ожирение первой степени";
            else if (bmi <= 40)
                kind = "Ожирение второй степени";
            else
                kind = "Ожирение третьей степени (морбидное)";
        } else {
            kind = "Младше 18, данные не доступны...";
        }
        HistoryItem item = historyItemFactory.getHistoryItem(age, gender, height, weight, bmi, pi, kind);
        if (item == null) {
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
            return;
        }// todo

        History history = (History) getServletContext().getAttribute(HISTORY_ATTRIBUT_NAME);
        history.push(item);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(item.toJson().toString());
    }
}
