import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOService {
    private final Connection connection = DAOConnection.getConnection();

    private final String SELECT_ALL = "select * from employee";
    private final String INSERT_EMPLOYEE = "insert into employee(name,email,address,phone,salary,department) " +
            "value(?,?,?,?,?,?);";
    private final String SELECT_BY_ID = "select * from employee where id =?;";
    private final String UPDATE_EMPLOYEE = "update employee set name=?,email=?, address=?,phone=?,salary=?,department=? where id =?;";
    private final String DELETE_EMPLOYEE = "delete from employee where id=?;";
    private final String SEARCH_BY_NAME = "select id,name,email,address,phone,salary,department from employee where name like concat('%' , ? , '%');";

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Employee employee = getEmployeeInResulSet(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhone());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getDepartment());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee findById(int id) {
        Employee employee = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                String department = resultSet.getString("department");
                employee = new Employee(id, name, email, address, phone, salary, department);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public void editEmployee(int id, Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhone());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getDepartment());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SEARCH_BY_NAME)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = getEmployeeInResulSet(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    private static Employee getEmployeeInResulSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String phone =  resultSet.getString("phone");
        double salary = resultSet.getDouble("salary");
        String department = resultSet.getString("department");
        Employee employee   = new Employee(id, name, email, address, phone,salary,department);
        return employee;
    }
}
//id,name,email,address,phone,salary,department