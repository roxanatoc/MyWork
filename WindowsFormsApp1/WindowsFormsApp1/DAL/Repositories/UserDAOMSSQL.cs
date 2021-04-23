using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1.DAL.Interfaces;

namespace WindowsFormsApp1
{
    class UserDAOMSSQL : IUserDAO

    {
        private static UserDAOMSSQL _usersDAL = null;
        private String _connectionString = @"Data Source=LENOVO-PC\SQLEXPRESS;Initial Catalog=ps;Integrated Security=True";
        SqlConnection _conn = null;

        public UserDAOMSSQL()
        {
            try
            {
                _conn = new SqlConnection(_connectionString);
            }
            catch (SqlException e)
            {
                
                //de facut ceva error handling, afisat mesaj, etc.. 
                _conn = null;
            }

        }

        public static UserDAOMSSQL getInstance()
        {
            if (_usersDAL == null)
            {
                _usersDAL = new UserDAOMSSQL();
            }
            return _usersDAL;
        }

        public User getUser(String username, String password)
        {
            User u = null;
            String sql = "SELECT * FROM dbo.users WHERE username='" + username + "' AND password='" + password + "'";

            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                reader.Read();
                u = new User(reader["username"].ToString(), reader["password"].ToString(), reader["name"].ToString(), reader["role"].ToString());
                _conn.Close();
            }
            catch (System.InvalidOperationException)
            {
             
                return null;
            }

            return u;

        }

        public void addEmployee(string username, string password, String name)
        {
            String sql = "INSERT INTO [users](username, password, name, role) VALUES ('" + username + "','" + password + "','" + name + "','" + "employee" + "')";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                cmd.ExecuteNonQuery();
                Console.WriteLine(sql);
                _conn.Close();
                MessageBox.Show("Succes!");
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);

            }
        }
    }
}
