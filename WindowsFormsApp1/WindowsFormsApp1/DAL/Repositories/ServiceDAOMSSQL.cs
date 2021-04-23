using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1.DAL.Interfaces;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.DAL.Repositories
{
    class ServiceDAOMSSQL : IServicesDAO
    {
        private String _connectionString = @"Data Source=LENOVO-PC\SQLEXPRESS;Initial Catalog=ps;Integrated Security=True";
        SqlConnection _conn = null;

        public ServiceDAOMSSQL()
        {
            try
            {
                _conn = new SqlConnection(_connectionString);
            }
            catch (SqlException e)
            {
                _conn = null;
            }

        }
        public void addService(Service services)
        {
            String sql = "INSERT INTO [servicii](serviceName,price) VALUES('" + services.getServiceName() + "','" + services.getPrice() + "')";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                cmd.ExecuteNonQuery();
                _conn.Close();
                MessageBox.Show("Succes!");
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);

            }

        }

        public Service viewService(String serviceName)
        {
            Service service = null;
            String sql =  "SELECT * FROM dbo.servicii WHERE serviceName = '" + serviceName + "'";

            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                reader.Read();
                service = new Service(reader["serviceName"].ToString(), reader["price"].ToString());
                _conn.Close();
                MessageBox.Show("Service Name: " + service.getServiceName() + " \nPrice:  " + service.getPrice());
                
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
                MessageBox.Show("Couldn't find the service with name: " + serviceName);
                _conn.Close();
                return null;
            }

            return service;
        }

        public void removeService(string serviceName)
        {
            String sql = "DELETE FROM ps.dbo.servicii WHERE serviceName= '" + serviceName + "';";
            Console.WriteLine(sql);
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                cmd.ExecuteNonQuery();
                _conn.Close();
                MessageBox.Show("Succes!");
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
                MessageBox.Show("Couldn't find the service with name: " + serviceName);
                _conn.Close();
            }
        }

        public void updateService(Service service)
        {
            String sql = "Update dbo.servicii SET price = " + service.getPrice() + " WHERE serviceName = '" + service.getServiceName() + "'";
            Console.WriteLine(sql);
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    service.setServiceName(reader["serviceName"].ToString());
                    service.setPrice(reader["price"].ToString());
                    _conn.Close();
                }
                MessageBox.Show("Succes!");
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
                MessageBox.Show("Couldn't find the service with name: " + service.getServiceName());
                _conn.Close();
            }
        }
    }
}
