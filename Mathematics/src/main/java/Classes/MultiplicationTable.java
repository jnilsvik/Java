package Classes;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class MultiplicationTable {

    public void makeOneMultiplicationTable(JspWriter out, int number, int upperLimit) throws IOException {
        int index = 1;
        int result = index * number;
        out.println("<div class=\"result\">"
                + "<legend>Multiplication table of number " + number + " with an upper limit till " + upperLimit + " :</legend><br>");
        for (index = 1; index <= upperLimit; index++) {
            out.println("<table>\n" +
                    "<tr>\n" +
                    "<th>" + number + "</th>\n" +
                    "<th>*</th>\n" +
                    "<th>" + index + "</th>\n" +
                    "<th>=</th>\n" +
                    "<th>" + result + "</th>\n" +
                    "</table>");
        }
    }

    public void makeSeveralMultiplicationTables(JspWriter out, int quantity, int upperLimit) throws IOException {
        int index = 1;
        while (index <= quantity) {
            makeOneMultiplicationTable(out, index, upperLimit);
            out.println("</div>");
            index++;
        }
    }
}