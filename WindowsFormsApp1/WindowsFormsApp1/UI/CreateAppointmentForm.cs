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
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.UI
{
    public partial class CreateAppointmentForm : Form
    {
        public CreateAppointmentForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String date = textBox1.Text;
            String hour = textBox2.Text;
            String clientName = textBox3.Text;
            String phone = textBox4.Text;
            String services = textBox5.Text;

            Appointment appointment = new Appointment(date, hour, clientName, phone, services);

            AppointmentService appointmentService = new AppointmentService(new AppointmentDAOMSSQL());
            appointmentService.addAppointment(appointment);
            appointmentService.costAppointment(appointment);

            textBox1.Clear();
            textBox2.Clear();
            textBox3.Clear();
            textBox4.Clear();
            textBox5.Clear();
        }

        private void CreateAppointmentForm_Load(object sender, EventArgs e)
        {

        }

    }
}
