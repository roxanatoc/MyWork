using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsFormsApp1.DAL.Interfaces;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.BL
{
    class AppointmentService
    {
        IAppointmentDAO _repository;

        public AppointmentService(IAppointmentDAO repository)
        {
            _repository = repository;
        }

        public void addAppointment(Appointment appointment)
        {
            _repository.addAppointment(appointment);
        }
        public void findAppointments(string date)
        {
            _repository.findAppointments(date);
        }

        public void findAppointment(string clientName)
        {
            _repository.findAppointment(clientName);
        }

        public void costAppointment(Appointment appointment)
        {
            _repository.costAppointment(appointment);
        }

        public void reportInterval(String date1, String date2)
        {
            _repository.reportInterval(date1, date2);
        }

    }
}
