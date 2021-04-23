using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.DAL.Interfaces
{
    interface IServicesDAO
    {
        void addService(Service services);
        Service viewService(String serviceName);
        void updateService(Service service);
        void removeService(String serviceName);
    }
}
