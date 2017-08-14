package controller;
/**
 * Created by genya on 11/08/2017.
 */

import model.Result;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Math.pow;

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

}
