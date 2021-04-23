using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Security.Cryptography;
using WindowsFormsApp1.BL;
using WindowsFormsApp1.UI;

namespace WindowsFormsApp1
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            String username = textBox1.Text;
            String password = textBox2.Text;
            UserService userService = new UserService(new UserDAOMSSQL());

            User u = userService.login(username, password);

            if (u == null)
            {
                WarningForm warningForm = new WarningForm();
                warningForm.Show();
            }


            else if (u.getRole().Equals("admin"))
            {
                AdminForm adminForm = new AdminForm();
                adminForm.Show();
            }
            else
            {
                if (u.getRole().Equals("employee"))
                {
                    EmployeeForm userForm = new EmployeeForm();
                    userForm.Show();
                }
            }

            textBox1.Clear();
            textBox2.Clear();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void Login_Load(object sender, EventArgs e)
        {

        }
    }
}
