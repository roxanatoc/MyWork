using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Windows.Forms;
using WindowsFormsApp1.DAL.Interfaces;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.DAL.Repositories
{
    class AppointmentDAOMSSQL : IAppointmentDAO
    {
        private String _connectionString = @"Data Source=LENOVO-PC\SQLEXPRESS;Initial Catalog=ps;Integrated Security=True";
        SqlConnection _conn = null;

        public AppointmentDAOMSSQL()
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

        public void addAppointment(Appointment appointment)
        {
            String service = appointment.getServices();
            string[] serviceName = service.Split(' ', ',');
            int ok = 1;

            String sql1 = "SELECT * FROM appointment WHERE date = '" + appointment.getDate() + "'";
            try
            {
                SqlConnection connection = this._conn;
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql1, _conn);
                SqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    string serv = reader["services"].ToString();
                    string[] serv1 = serv.Split(',', ' ');
                    String hours = reader["hour"].ToString();

                    if (serv1[0] == serviceName[0])
                    {
                        if (hours == appointment.getHour())
                        {
                            MessageBox.Show("We can't make this appointment because is already ocupied!");
                            ok = 0;
                            connection.Close();
                        }
                    }
                }
                connection.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
            if (ok == 1)
            {
                String sql = "INSERT INTO [appointment](date, hour, clientName, phone, services) VALUES('" + appointment.getDate() + "','" + appointment.getHour() + "','" + appointment.getClientName() + "','" + appointment.getPhone() + "','" + appointment.getServices() + "')";
                try
                {
                    _conn.Open();
                    SqlCommand cmd = new SqlCommand(sql, _conn);
                    cmd.ExecuteNonQuery();
                    Console.WriteLine(sql);
                    _conn.Close();
                }
                catch (SqlException e)
                {
                    Console.WriteLine(e.Message);

                }
            }
           
        }

        public void findAppointments(string date)
        {
            string list = "";
            string sql = "SELECT * FROM [appointment] WHERE date='" + date + "'";

            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    list += "Date: " + reader["date"].ToString() + "\n" + "Hour: " + reader["hour"].ToString() + "\n" + "Client name: " +  reader["clientName"].ToString() + "\n" + "Phone: " + reader["phone"].ToString() + "\n" + "Services: " + reader["services"].ToString() + "\n\n";
                }
                MessageBox.Show(list);
                _conn.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void findAppointment(string clientName)
        {
            string list = "";
            string sql = "SELECT * FROM [appointment] WHERE clientName='" + clientName + "'";

            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    list += "Client name: " + reader["clientName"].ToString() + "\n" + "Date: " + reader["date"].ToString() + "\n" + "Hour: " + reader["hour"].ToString() + "\n" + "Phone: " + reader["phone"].ToString() + "\n" + "Services: " + reader["services"].ToString() + "\n\n";
                }
                MessageBox.Show(list);
                _conn.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void reportInterval(String date1, String date2)
        {
            String[] d1 = date1.Split('.');
            String[] d2 = date2.Split('.');

            DateTime datee1 = new DateTime(Int32.Parse(d1[2]), Int32.Parse(d1[1]), Int32.Parse(d1[0]));
            Console.WriteLine(datee1);
            DateTime datee2 = new DateTime(Int32.Parse(d2[2]), Int32.Parse(d2[1]), Int32.Parse(d2[0]));
            Console.WriteLine(datee2);

            List<String> list = new List<String>();
            
            String sql = "SELECT * FROM appointment ";
            try
            {
                _conn.Open();
                SqlCommand cmd = new SqlCommand(sql, _conn);
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    string date = reader["date"].ToString();
                    string[] d11 = date.Split('.');
                    DateTime d3 = new DateTime(Int32.Parse(d11[2]), Int32.Parse(d11[1]), Int32.Parse(d11[0]));
                    Console.WriteLine(d3);
                    DateTime[] dates = new DateTime[] { datee1, datee2, d3 };
                    if (datee1 == datee2 && datee2 == d3)
                    {
                        Console.WriteLine("All dates are the same");
                    }
                    else
                    {
                        Array.Sort(dates);
                    }
                    int final = DateTime.Compare(dates[1], d3);
                    if (final == 0)
                        list.Add("Date: " + date + "  Hour: " + reader["hour"].ToString() + "  clientName: " + reader["clientName"].ToString() + "  Phone: " + reader["phone"].ToString() + "  Services:" + reader["services"].ToString() + "\n\n");
                }
                string s = "";
                foreach (string l in list)
                { 
                    s = s + l;
                }
                MessageBox.Show(s);

                _conn.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }

        }

        public void costAppointment(Appointment appointment)
        {
            float total = 0;

            String service = appointment.getServices();
            string[] serviceName = service.Split(' ', ',');

            foreach (String serv in serviceName)
            {
              string sql = "SELECT price from servicii where serviceName = '" + serv + "'";

                try
                 {
                     _conn.Open();
                     SqlCommand cmd = new SqlCommand(sql, _conn);
                     SqlDataReader reader = cmd.ExecuteReader();
                     while (reader.Read())
                     {
                        string s = reader["price"].ToString();
                        float val = float.Parse(s);
                        Console.WriteLine(val);
                        total += val;
                     }

                     _conn.Close();
                }
                catch (SqlException e)
                {
                     e.ToString();
                }
            }
            MessageBox.Show("Total cost: " + total.ToString());

        }
    }
}
