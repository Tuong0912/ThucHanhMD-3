import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    DAOService daoService = new DAOService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acion = request.getParameter("action");
        if (acion == null) {
            acion = "";
        }
        switch (acion) {
            case "create":
                formCreate(request, response);
                break;
            case "edit":
                editForm(request, response);
                break;
            case "delete":
                formDelete(request, response);
                break;
            default:
                listEmployy(request, response);

        }
    }

    private void formDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.daoService.findById(id);
        if (employee == null) {
            response.sendRedirect("/EmployeeServlet");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete.jsp");
            request.setAttribute("employee", employee);
            requestDispatcher.forward(request, response);
        }
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.daoService.findById(id);
        if (employee == null) {
            response.sendRedirect("/EmployeeServlet");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit.jsp");
            request.setAttribute("employee", employee);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void formCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listEmployy(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = this.daoService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        request.setAttribute("employee", employees);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acion = request.getParameter("action");
        if (acion == null) {
            acion = "";
        }
        switch (acion) {
            case "create":
                createEmployy(request, response);
                break;
            case "edit":
                editEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "search":
                searchEmployee(request, response);
            default:
                listEmployy(request, response);

        }
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        List<Employee> employee = this.daoService.findByName(name);
        if (employee == null) {
            response.sendRedirect("/EmployeeServlet");
            System.out.println("bug");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            request.setAttribute("employee",employee);
            dispatcher.forward(request, response);//employee
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.daoService.findById(id);
        if (employee == null) {
            response.sendRedirect("/EmployeeServlet");
        } else {
            this.daoService.deleteEmployee(id);
            response.sendRedirect("/EmployeeServlet");
        }
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.daoService.findById(id);
        if (employee == null) {
            response.sendRedirect("/EmployeeServlet");
        } else {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            double salary = Double.parseDouble(request.getParameter("salary"));
            String department = request.getParameter("department");
            employee = new Employee(name, email, address, phone, salary, department);
            this.daoService.editEmployee(id, employee);
            response.sendRedirect("/EmployeeServlet");
        }

    }

    private void createEmployy(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String department = request.getParameter("department");
        Employee employee = new Employee( name, email, address, phone, salary, department);
        this.daoService.addEmployee(employee);

        try {
            response.sendRedirect("/EmployeeServlet");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
