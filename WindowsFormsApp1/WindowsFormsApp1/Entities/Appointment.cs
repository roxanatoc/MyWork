using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1.Entities
{
    class Appointment
    {
        private String date;
        private String hour;
        private String clientName;
        private String phone;
        private String services;

        public Appointment(String date, String hour, String clientName, String phone, String services)
        {
            this.date = date;
            this.hour = hour;
            this.clientName = clientName;
            this.phone = phone;
            this.services = services;
        }

        public String getDate()
        {
            return this.date;
        }

        public String getHour()
        {
            return this.hour;
        }

        public String getClientName()
        {
            return this.clientName;
        }

        public String getPhone()
        {
            return this.phone;
        }

        public String getServices()
        {
            return this.services;
        }
    }
}
