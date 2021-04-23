using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsFormsApp1.DAL.Interfaces;
using WindowsFormsApp1.DAL.Repositories;
using WindowsFormsApp1.Entities;

namespace WindowsFormsApp1.BL
{
    class ServicesService
    {
        IServicesDAO _repository;

        public ServicesService(IServicesDAO repository)
        {
            _repository = repository;
        }

        public void addService(Service services)
        {
            _repository.addService(services);
        }

        public void removeService(String serviceName)
        {
            _repository.removeService(serviceName);
        }

        public void updateService(Service service)
        {
            _repository.updateService(service);
        }


        public void viewService(string nameService)
        {
            _repository.viewService(nameService);
        }
    }
}
