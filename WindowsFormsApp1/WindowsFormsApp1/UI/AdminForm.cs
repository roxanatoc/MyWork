using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1.BL;
using WindowsFormsApp1.DAL.Repositories;
using WindowsFormsApp1.UI;

namespace WindowsFormsApp1
{
    public partial class AdminForm : Form
    {
        public AdminForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            NewAccountForm newAccountForm = new NewAccountForm();
            newAccountForm.Show();
        }
        private void button2_Click(object sender, EventArgs e)
        {
            ServicesManagementForm servicesManagementForm = new ServicesManagementForm();
            servicesManagementForm.Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            String date1 = textBox1.Text;
            String date2 = textBox2.Text;
            AppointmentService appointmentService = new AppointmentService(new AppointmentDAOMSSQL());
            appointmentService.reportInterval(date1, date2);

            textBox1.Clear();
            textBox2.Clear();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            String name = textBox3.Text;
            AppointmentService appointmentService = new AppointmentService(new AppointmentDAOMSSQL());
            appointmentService.findAppointment(name);

            textBox3.Clear();
        }
    }
}
