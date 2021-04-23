using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1.DAL.Interfaces
{
    interface IUserDAO
    {
        void addEmployee(String username, String password, String name);
        User getUser(String username, String password);
    }
}
