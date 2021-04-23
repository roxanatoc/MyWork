using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.DAL.Interfaces
{
    interface IAppointmentDAO
    {
        void addAppointment(Appointment appointment);
        void findAppointments(String date);
        void findAppointment(String clientName);
        void reportInterval(String date1, String date2);
        void costAppointment(Appointment appointment);
    }
}
