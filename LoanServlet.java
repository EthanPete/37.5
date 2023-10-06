<!DOCTYPE html>
<html>
<head>
    <title>Loan Calculator</title>
</head>
<body>
    <h1>Loan Calculator</h1>
    <form action="LoanServlet" method="post">
        <label for="loanAmount">Loan Amount:</label>
        <input type="text" id="loanAmount" name="loanAmount" required><br><br>

        <label for="interestRate">Interest Rate (%):</label>
        <input type="text" id="interestRate" name="interestRate" required><br><br>

        <label for="numOfYears">Number of Years:</label>
        <input type="text" id="numOfYears" name="numOfYears" required><br><br>

        <input type="submit" value="Compute Loan Payment">
    </form>
</body>
</html>



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the form data
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numOfYears = Integer.parseInt(request.getParameter("numOfYears"));

        // Calculate monthly and total payments using the Loan class (Listing 10.2)
        Loan loan = new Loan(annualInterestRate, numOfYears, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Display the results
        out.println("<html>");
        out.println("<head><title>Loan Payment Results</title></head>");
        out.println("<body>");
        out.println("<h2>Loan Payment Results</h2>");
        out.println("<p>Loan Amount: $" + loanAmount + "</p>");
        out.println("<p>Annual Interest Rate: " + annualInterestRate + "%</p>");
        out.println("<p>Number of Years: " + numOfYears + "</p>");
        out.println("<p>Monthly Payment: $" + monthlyPayment + "</p>");
        out.println("<p>Total Payment: $" + totalPayment + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
