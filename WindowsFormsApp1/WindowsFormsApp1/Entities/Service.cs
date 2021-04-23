using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1.Entities
{
    class Service
    {
        private String serviceName;
        private String price;
        public Service( String serviceName, String price)
        {
            this.serviceName = serviceName;
            this.price = price;
        }

        public String getServiceName()
        {
            return this.serviceName;
        }

        public String getPrice()
        {
            return this.price;
        }

        public void setServiceName(String serviceName)
        {
            this.serviceName = serviceName;
        }
        public void setPrice(String price)
        {
            this.price = price;
        }

    }
}
