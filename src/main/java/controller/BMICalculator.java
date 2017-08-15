package controller;
/**
 * Created by genya on 11/08/2017.
 */

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

@WebServlet(name="bmiCalculator",urlPatterns={"/calculator"})
public class BMICalculator extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        float weight = Float.parseFloat(req.getParameter("weight"));
        float height = Float.parseFloat(req.getParameter("height"));
        float bmi = (float) (weight / pow(height / 100,2));
        float pi = (float) (weight / pow(height / 100, 3));
        String kind = "Normal";
        Result result = new Result(bmi, pi, kind);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(result.toJson().toJSONString());

        ServletContext context = getServletContext();
        context.setAttribute("result", result);
    }

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

        float bmi = (float) (weight / pow (height, 2));
        float pi

    }
}
