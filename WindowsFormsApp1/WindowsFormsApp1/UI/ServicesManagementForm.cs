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
    public partial class ServicesManagementForm : Form
    {
        public ServicesManagementForm()
        {
            InitializeComponent();
        }


        private void ServicesManagementForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            String name = textBox1.Text;
            String price = textBox2.Text;
            Service service = new Service(name, price);

            ServicesService servicesService = new ServicesService(new ServiceDAOMSSQL());
            servicesService.addService(service);

            textBox1.Clear();
            textBox2.Clear();

        }

        private void button2_Click(object sender, EventArgs e)
        {
            String name = textBox1.Text;
            ServicesService servicesService = new ServicesService(new ServiceDAOMSSQL());
            servicesService.removeService(name);

            textBox1.Clear();

        }

        private void button3_Click(object sender, EventArgs e)
        {
            String name = textBox1.Text;
            String price = textBox2.Text;
            Service service = new Service(name, price);

            ServicesService servicesService = new ServicesService(new ServiceDAOMSSQL());
            servicesService.updateService(service);

            textBox1.Clear();
            textBox2.Clear();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            String name = textBox1.Text;
            ServicesService servicesService = new ServicesService(new ServiceDAOMSSQL());
            servicesService.viewService(name);

            textBox1.Clear();

        }
    }
}
